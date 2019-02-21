package cn.mallcloud.admin.registry.store;

import cn.mallcloud.admin.event.ClientApplicationDeregisteredEvent;
import cn.mallcloud.admin.event.ClientApplicationRegisteredEvent;
import cn.mallcloud.admin.model.Application;
import cn.mallcloud.admin.model.Instance;
import cn.mallcloud.admin.model.StatusInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 服务缓存hashMap实现
 *
 * @author zscat
 */
@Slf4j
public class SimpleApplicationStore implements ApplicationStore, ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    private final ConcurrentMap<String, Application> apps = new ConcurrentHashMap<>();

    @Override
    public void save(String serviceId, Instance instance) {
        if (apps.containsKey(serviceId)) {
            final Application app = apps.get(serviceId);
            final Instance instance1 = find(instance.getInstanceId());
            if (instance1 == null) {
                // 新实例注册
                app.getInstances().add(instance);
                publisher.publishEvent(new ClientApplicationRegisteredEvent(serviceId, instance));
            } else {
                // 刷新实例, 主要是为了刷新metadata
                app.getInstances().remove(instance1);
                app.getInstances().add(instance);
                apps.put(serviceId, app);
            }
        } else {
            // 新服务注册
            final Application app = new Application();
            app.setServiceId(serviceId);
            app.getInstances().add(instance);
            apps.put(serviceId, app);
            publisher.publishEvent(new ClientApplicationRegisteredEvent(serviceId, instance));
        }
    }

    @Override
    public Collection<Application> findAll() {
        return apps.values();
    }

    @Override
    public Instance find(String instanceId) {
        final Collection<Application> all = apps.values();
        for (Application app : all) {
            final Collection<Instance> instances = app.getInstances();
            for (Instance instance : instances) {
                if (instanceId.equals(instance.getInstanceId())) {
                    return instance;
                }
            }
        }
        return null;
    }

    @Override
    public void delete(String instanceId) {
        apps.values().forEach(app -> app.getInstances().forEach(instance -> {
            if (instance.getInstanceId().equals(instanceId)) {
                app.getInstances().remove(instance);
                publisher.publishEvent(new ClientApplicationDeregisteredEvent(app.getServiceId(), instance));
            }
        }));
    }

    @Override
    public void statusChange(String serviceId, String instanceId, StatusInfo from, StatusInfo to) {
        final Instance instance1 = apps.get(serviceId).getInstances()
                .stream()
                .filter(instance -> instance.getInstanceId().equals(instanceId))
                .findFirst().orElse(null);
        if (instance1 != null) {
            // 检查状态是否匹配
            if (!instance1.getStatus().equals(from.getStatus())) {
                log.warn("app instance old status not match. " + serviceId + ":" + instanceId);
            }
            instance1.setStatus(to.getStatus());
        }
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }
}
