package cn.mallcloud.rbac.admin.service.impl;

import cn.mallcloud.common.constants.CommonConstant;
import cn.mallcloud.common.entity.SysZuulRoute;
import cn.mallcloud.common.redis.template.MallcloudRedisRepository;
import cn.mallcloud.common.utils.JsonUtils;
import cn.mallcloud.rbac.admin.mapper.SysZuulRouteMapper;
import cn.mallcloud.rbac.admin.service.SysZuulRouteService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 动态路由配置表 服务实现类
 *
 * @author zscat
 * @since 2018-05-15
 */
@Service
public class SysZuulRouteServiceImpl extends ServiceImpl<SysZuulRouteMapper, SysZuulRoute> implements SysZuulRouteService {

    @Autowired
    private MallcloudRedisRepository redisRepository;

    /**
     * 同步路由配置信息,到服务网关
     *
     * @return 同步成功
     */
    @Override
    public Boolean applyZuulRoute() {
        EntityWrapper<SysZuulRoute> wrapper = new EntityWrapper<>();
        wrapper.eq(CommonConstant.DEL_FLAG, CommonConstant.STATUS_NORMAL);
        List<SysZuulRoute> routeList = selectList(wrapper);
        redisRepository.set(CommonConstant.ROUTE_KEY, JsonUtils.toJsonString(routeList));
        return Boolean.TRUE;
    }
}
