package cn.mallcloud.admin.registry;

import cn.mallcloud.admin.config.MallcloudAdminServerProperties;
import cn.mallcloud.admin.converter.ServiceInstanceConverter;
import cn.mallcloud.admin.event.ClientApplicationStatusChangedEvent;
import cn.mallcloud.admin.model.Application;
import cn.mallcloud.admin.model.Instance;
import cn.mallcloud.admin.model.StatusInfo;
import cn.mallcloud.admin.registry.store.ApplicationStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 服务监听缓存
 *
 * @author zscat
 * @date 2017/11/26 11:22
 */
public class ApplicationRegistry implements ApplicationEventPublisherAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationRegistry.class);

    private final ApplicationStore applicationStore;

    private final DiscoveryClient discoveryClient;

    private final ServiceInstanceConverter instanceConverter;

    private final TaskScheduler taskScheduler;

    private final MallcloudAdminServerProperties serverProperties;

    private final RestTemplate restTemplate;

    private ApplicationEventPublisher eventPublisher;

    public ApplicationRegistry(ApplicationStore applicationStore, DiscoveryClient discoveryClient, ServiceInstanceConverter instanceConverter, TaskScheduler taskScheduler, MallcloudAdminServerProperties serverProperties, RestTemplate restTemplate) {
        this.applicationStore = applicationStore;
        this.discoveryClient = discoveryClient;
        this.instanceConverter = instanceConverter;
        this.taskScheduler = taskScheduler;
        this.serverProperties = serverProperties;
        this.restTemplate = restTemplate;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }

    /**
     * Application容器启动成功以后, 从注册中心读取并缓存所有服务实例
     */
    private void registryApps() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("starting registry app from discoveryClient...");
        }
        List<String> serviceIds = discoveryClient.getServices();
        for (String serviceId : serviceIds) {
            final Collection<Instance> instances = getInstancesByServiceId(serviceId);
            instances.forEach(instance -> applicationStore.save(serviceId, instance));
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("registry apps from discoveryClient: {}", applicationStore.findAll());
        }
    }

    /**
     * 根据服务名称获取 Application列表
     *
     * @param serviceId 服务名称
     * @return Collection<Application>
     */
    private Collection<Instance> getInstancesByServiceId(String serviceId) {
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
        return instanceConverter.convert(serviceId, instances);
    }

    public void initInterval() {
        taskScheduler.scheduleAtFixedRate(() -> {
            // 刷新apps
            registryApps();
            Collection<Application> apps = applicationStore.findAll();
            // 循环刷新app 状态
            for (Application app : apps) {
                taskScheduler.schedule(new AppRefresh(app, restTemplate), new Date());
            }
        }, serverProperties.getMonitor().getStatusRefreshIntervalInMills());
    }

    public Collection<Application> getApplications() {
        return applicationStore.findAll();
    }

    private class AppRefresh implements Runnable {
        private final Application app;

        private final RestTemplate restTemplate;

        AppRefresh(Application app, RestTemplate restTemplate) {
            this.app = app;
            this.restTemplate = restTemplate;
        }

        @Override
        public void run() {
            final Collection<Instance> instances = app.getInstances();
            instances.forEach(this::doRefresh);
        }

        /**
         * 刷新实例 检查实例状态
         *
         * @param instance
         */
        private void doRefresh(Instance instance) {
            final String healthUrl = instance.getHealthCheckUrl();
            if (StringUtils.isEmpty(healthUrl)) {
                if (LOGGER.isWarnEnabled()) {
                    LOGGER.warn("Application: {}, has empty healthCheckUrl!", instance.getInstanceId());
                }
            } else {
                StatusInfo from = StatusInfo.valueOf(instance.getStatus());
                try {
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("Fetching Application Health '{}' for {}", healthUrl, instance);
                    }
                    ResponseEntity<Map> response = restTemplate.exchange(healthUrl, HttpMethod.GET, null, Map.class);
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("'{}' responded with {}", healthUrl, response);
                    }

                    Map map = response.getBody();
                    if (!CollectionUtils.isEmpty(map) && map.containsKey(StatusInfo.STATUS_KEY)) {
                        StatusInfo to = StatusInfo.valueOf(String.valueOf(map.get(StatusInfo.STATUS_KEY)));
                        if (!from.getStatus().equals(to.getStatus())) {
                            // 状态变更
                            eventPublisher.publishEvent(new ClientApplicationStatusChangedEvent(app.getServiceId(),
                                    instance, from,
                                    to));
                            if (LOGGER.isErrorEnabled()) {
                                LOGGER.error("APP: {}, Status Changed from :{} to :{}", instance.getInstanceId() + "-" + instance.getHealthCheckUrl(),
                                        from.toString(), to.toString());
                            }
                        }
                    }
                } catch (Exception e) {
                    if (LOGGER.isErrorEnabled()) {
                        LOGGER.error("APP: {}, health check failed", instance.getInstanceId() + "-" + instance.getHealthCheckUrl());
                    }
                    if (from.isUp()) {
                        // 只有当app 原来的状态是UP的时候,才发布事件
                        // 因为有可能app是本来就是down的,再发布事件就没有意义.
                        StatusInfo to = StatusInfo.ofDown();
                        eventPublisher.publishEvent(new ClientApplicationStatusChangedEvent(app.getServiceId(),
                                instance, from, to));
                        if (LOGGER.isErrorEnabled()) {
                            LOGGER.error("APP: {}, Status Changed from :{} to :{}", instance.getInstanceId() + "-" + instance.getHealthCheckUrl(),
                                    from.toString(), to.toString());
                        }
                    }
                }
            }
        }
    }
}
