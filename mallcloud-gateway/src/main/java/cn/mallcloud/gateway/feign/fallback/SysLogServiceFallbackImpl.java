package cn.mallcloud.gateway.feign.fallback;

import cn.mallcloud.common.entity.SysLog;
import cn.mallcloud.gateway.feign.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author zscat
 * @date 2018/9/13 17:09
 */
@Slf4j
@Service
public class SysLogServiceFallbackImpl implements SysLogService {

    @Override
    public void add(SysLog sysLog) {
        log.error("调用{}异常{}", "addSysLog", sysLog);
    }
}
