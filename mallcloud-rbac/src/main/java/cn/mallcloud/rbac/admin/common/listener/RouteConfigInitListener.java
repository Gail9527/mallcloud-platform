package cn.mallcloud.rbac.admin.common.listener;

import cn.mallcloud.common.constants.CommonConstant;
import cn.mallcloud.common.entity.SysZuulRoute;
import cn.mallcloud.common.redis.template.MallcloudRedisRepository;
import cn.mallcloud.common.utils.JsonUtils;
import cn.mallcloud.rbac.admin.service.SysZuulRouteService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;


/**
 * @author zscat
 * @date 2018/5/16
 */
@Slf4j
@Component
public class RouteConfigInitListener implements ApplicationListener<ApplicationStartedEvent> {

    @Autowired
    private MallcloudRedisRepository redisRepository;

    @Autowired
    private SysZuulRouteService sysZuulRouteService;

    /**
     * Callback used to run the bean.
     * 初始化路由配置的数据，避免gateway 依赖业务模块
     */
    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        log.info("开始初始化路由配置数据");
        EntityWrapper<SysZuulRoute> wrapper = new EntityWrapper<>();
        wrapper.eq(CommonConstant.DEL_FLAG, CommonConstant.STATUS_NORMAL);
        List<SysZuulRoute> routeList = sysZuulRouteService.selectList(wrapper);
        if (!CollectionUtils.isEmpty(routeList)) {
            redisRepository.set(CommonConstant.ROUTE_KEY, JsonUtils.toJsonString(routeList));
            log.info("更新Redis中路由配置数据：{}条", routeList.size());
        }
        log.info("初始化路由配置数据完毕");
    }
}
