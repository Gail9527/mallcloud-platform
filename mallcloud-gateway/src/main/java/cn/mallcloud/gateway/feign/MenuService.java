package cn.mallcloud.gateway.feign;

import cn.mallcloud.common.constants.ServiceNameConstants;
import cn.mallcloud.common.vo.MenuVO;
import cn.mallcloud.gateway.feign.fallback.MenuServiceFallbackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

/**
 * @author zscat
 * @date 2017/10/31
 */
@FeignClient(name = ServiceNameConstants.RBAC_SERVICE, fallback = MenuServiceFallbackImpl.class)
public interface MenuService {
    /**
     * 通过角色名查询菜单
     *
     * @param role 角色名称
     * @return 菜单列表
     */
    @GetMapping(value = "/menu/findMenuByRole/{role}")
    Set<MenuVO> findMenuByRole(@PathVariable("role") String role);
}
