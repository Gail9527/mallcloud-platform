/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.mallcloud.admin.converter;


import cn.mallcloud.admin.model.Instance;
import cn.mallcloud.admin.model.LeaseInfo;
import com.netflix.appinfo.InstanceInfo;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * ServiceInstanceConverter 实现
 *
 * @author zscat
 */
public class DefaultServiceInstanceConverter implements ServiceInstanceConverter {

    @Override
    public Collection<Instance> convert(String serviceId, Collection<ServiceInstance> instances) {
        return instances
                .stream()
                .map(instance -> {
                    if (instance instanceof EurekaDiscoveryClient.EurekaServiceInstance) {
                        final EurekaDiscoveryClient.EurekaServiceInstance eurekaServiceInstance = (EurekaDiscoveryClient
                                .EurekaServiceInstance) instance;
                        final InstanceInfo instanceInfo = eurekaServiceInstance.getInstanceInfo();
                        return createInstance(instanceInfo);
                    }
                    return null;
                }).collect(Collectors.toList());
    }

    /**
     * {@link InstanceInfo} 转 {@link Instance}
     *
     * @param instanceInfo InstanceInfo
     * @return Instance
     */
    private Instance createInstance(InstanceInfo instanceInfo) {
        final Instance instance = new Instance();
        instance.setInstanceId(instanceInfo.getInstanceId());
        instance.setHostName(instanceInfo.getHostName());
        instance.setAppName(instanceInfo.getAppName());
        instance.setIpAddr(instanceInfo.getIPAddr());
        instance.setStatus(instanceInfo.getStatus().name());
        instance.setOverriddenstatus(instanceInfo.getOverriddenStatus().name());
        instance.setPort(instanceInfo.getPort());
        instance.setSecurePort(instanceInfo.getSecurePort());
        instance.setMetadata(instanceInfo.getMetadata());
        instance.setHomePageUrl(instanceInfo.getHomePageUrl());
        instance.setStatusPageUrl(instanceInfo.getStatusPageUrl());
        instance.setHealthCheckUrl(instanceInfo.getHealthCheckUrl());
        instance.setVipAddress(instanceInfo.getVIPAddress());
        instance.setSecureVipAddress(instanceInfo.getSecureVipAddress());
        instance.setCoordinatingDiscoveryServer(instanceInfo.isCoordinatingDiscoveryServer());
        instance.setLastUpdatedTime(new Date(instanceInfo.getLastUpdatedTimestamp()));
        instance.setLastDirtyTime(new Date(instanceInfo.getLastDirtyTimestamp()));
        instance.setActionType(instanceInfo.getActionType().name());
        final LeaseInfo leaseInfo = new LeaseInfo();
        leaseInfo.setRenewalIntervalInSecs(instanceInfo.getLeaseInfo().getRenewalIntervalInSecs());
        leaseInfo.setDurationInSecs(instanceInfo.getLeaseInfo().getDurationInSecs());
        leaseInfo.setRegistrationTime(new Date(instanceInfo.getLeaseInfo().getRegistrationTimestamp()));
        leaseInfo.setLastRenewalTime(new Date(instanceInfo.getLeaseInfo().getRenewalTimestamp()));
        leaseInfo.setEvictionTime(new Date(instanceInfo.getLeaseInfo().getEvictionTimestamp()));
        leaseInfo.setServiceUpTime(new Date(instanceInfo.getLeaseInfo().getServiceUpTimestamp()));

        instance.setLeaseInfo(leaseInfo);
        return instance;
    }
}
