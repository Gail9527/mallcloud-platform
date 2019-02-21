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

import cn.mallcloud.admin.model.Application;
import cn.mallcloud.admin.model.Instance;
import org.springframework.cloud.client.ServiceInstance;

import java.util.Collection;

/**
 * 转换 {@link ServiceInstance}s to {@link Application}s.
 *
 * @author zscat
 */
public interface ServiceInstanceConverter {

    /**
     * 转换 {@link ServiceInstance}s to {@link Application}
     *
     * @param serviceId 服务id
     * @param instances  服务实例集合
     * @return Application
     */
    Collection<Instance> convert(String serviceId, Collection<ServiceInstance> instances);
}
