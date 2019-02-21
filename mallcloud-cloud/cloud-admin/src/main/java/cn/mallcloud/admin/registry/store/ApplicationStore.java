/*
 * Copyright 2013-2014 the original author or authors.
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
package cn.mallcloud.admin.registry.store;

import cn.mallcloud.admin.model.Application;
import cn.mallcloud.admin.model.Instance;
import cn.mallcloud.admin.model.StatusInfo;

import java.util.Collection;

/**
 * 服务缓存接口
 *
 * @author zscat
 */
public interface ApplicationStore {

    /**
     * 缓存服务实例
     *
     * @param serviceId 服务id
     * @param instance  服务实例
     */
    void save(String serviceId, Instance instance);

    /**
     * 返回所有服务列表,包括down掉的服务
     *
     * @return Collection<Application>
     */
    Collection<Application> findAll();

    /**
     * 查询实例详情
     *
     * @param instanceId 服务实例id
     * @return Application
     */
    Instance find(String instanceId);

    /**
     * 根据实例id 删除服务, 也要在downmap中找
     *
     * @param instanceId 实例id
     */
    void delete(String instanceId);

    /**
     * 服务实例状态变更
     *
     * @param serviceId  服务id
     * @param instanceId 实例id
     * @param from       旧状态
     * @param to         新状态
     */
    void statusChange(String serviceId, String instanceId, StatusInfo from, StatusInfo to);
}
