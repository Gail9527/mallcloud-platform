package cn.mallcloud.gateway.feign;

import cn.mallcloud.common.constants.ServiceNameConstants;
import cn.mallcloud.common.entity.SysLog;
import cn.mallcloud.gateway.feign.fallback.SysLogServiceFallbackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 系统日志Service
 *
 * @author zscat
 * @date 2018/9/13 17:06
 */
@FeignClient(name = ServiceNameConstants.RBAC_SERVICE, fallback = SysLogServiceFallbackImpl.class)
public interface SysLogService {

    /**
     * 添加日志
     *
     * @param log 日志实体
     */
    @PostMapping("/log")
    void add(@RequestBody SysLog log);
}
