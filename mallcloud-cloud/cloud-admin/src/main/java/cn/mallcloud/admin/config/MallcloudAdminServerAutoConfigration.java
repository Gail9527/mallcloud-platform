package cn.mallcloud.admin.config;

import cn.mallcloud.admin.converter.DefaultServiceInstanceConverter;
import cn.mallcloud.admin.converter.ServiceInstanceConverter;
import cn.mallcloud.admin.event.ClientApplicationDeregisteredEvent;
import cn.mallcloud.admin.event.ClientApplicationRegisteredEvent;
import cn.mallcloud.admin.event.ClientApplicationStatusChangedEvent;
import cn.mallcloud.admin.event.RoutesOutdatedEvent;
import cn.mallcloud.admin.model.Instance;
import cn.mallcloud.admin.model.StatusInfo;
import cn.mallcloud.admin.registry.ApplicationRegistry;
import cn.mallcloud.admin.registry.store.ApplicationStore;
import cn.mallcloud.admin.registry.store.SimpleApplicationStore;
import cn.mallcloud.admin.web.client.BasicAuthHttpHeaderProvider;
import cn.mallcloud.admin.web.client.HttpHeadersProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 服务治理配置
 *
 * @author zscat
 * @date 2017/11/26 20:58
 */
@Configuration
@EnableConfigurationProperties(MallcloudAdminServerProperties.class)
@ConditionalOnProperty(prefix = "mallcloud.admin", value = "enabled", havingValue = "true", matchIfMissing = true)
public class MallcloudAdminServerAutoConfigration {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Bean
    @ConditionalOnMissingBean(ApplicationStore.class)
    public ApplicationStore applicationStore() {
        return new SimpleApplicationStore();
    }

    @Bean("taskScheduler")
    public TaskScheduler taskScheduler(MallcloudAdminServerProperties serverProperties) {
        final ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(serverProperties.getTask().getPoolSize());
        scheduler.setWaitForTasksToCompleteOnShutdown(true);
        scheduler.setThreadNamePrefix("mallcloud-admin-registrypool-");
        scheduler.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return scheduler;
    }

    @Bean
    @ConditionalOnMissingBean(ServiceInstanceConverter.class)
    public ServiceInstanceConverter instanceConverter() {
        return new DefaultServiceInstanceConverter();
    }

    @Bean
    @ConditionalOnMissingBean(HttpHeadersProvider.class)
    public HttpHeadersProvider httpHeadersProvider() {
        return new BasicAuthHttpHeaderProvider();
    }

    @Bean
    @ConditionalOnMissingBean(ApplicationRegistry.class)
    public ApplicationRegistry applicationRegistry(ApplicationStore applicationStore,
                                                   DiscoveryClient discoveryClient,
                                                   ServiceInstanceConverter instanceConverter,
                                                   TaskScheduler taskScheduler,
                                                   MallcloudAdminServerProperties serverProperties,
                                                   RestTemplateBuilder restTemplBuilder) {
        RestTemplateBuilder builder = restTemplBuilder.messageConverters(new MappingJackson2HttpMessageConverter())
                .errorHandler(new DefaultResponseErrorHandler() {
                    @Override
                    protected boolean hasError(HttpStatus statusCode) {
                        return false;
                    }
                });
        builder = builder.setConnectTimeout(serverProperties.getMonitor().getConnectTimeout())
                .setReadTimeout(serverProperties.getMonitor().getReadTimeout());

        return new ApplicationRegistry(applicationStore, discoveryClient, instanceConverter,
                taskScheduler, serverProperties, builder.build());
    }

    @EventListener
    public void onClientApplicationRegistered(ClientApplicationRegisteredEvent event) {
        // 触发zuul 路由刷新
        publisher.publishEvent(new RoutesOutdatedEvent());
    }

    @EventListener
    public void onClientApplicationDeregistered(ClientApplicationDeregisteredEvent event) {
        // 触发zuul 路由刷新
        publisher.publishEvent(new RoutesOutdatedEvent());
    }

    /**
     * Spring容器启动成功,开启服务监听
     */
    @EventListener
    public void onApplicationReady(ApplicationReadyEvent applicationReadyEvent) {
        final ApplicationRegistry registry = applicationReadyEvent.getApplicationContext().getBean(ApplicationRegistry.class);
        registry.initInterval();
    }

    /**
     * 服务状态变更
     */
    @EventListener
    public void onClientApplicationStatusChangedEvent(ClientApplicationStatusChangedEvent event) {
        final StatusInfo from = event.getFrom();
        final StatusInfo to = event.getTo();
        final ApplicationStore store = applicationStore();
        final Instance instance = event.getInstance();
        store.statusChange(event.getServiceId(), instance.getInstanceId(), from, to);
    }

}
