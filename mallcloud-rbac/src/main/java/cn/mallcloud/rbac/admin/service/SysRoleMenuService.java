package cn.mallcloud.rbac.admin.service;


import cn.mallcloud.rbac.admin.model.entity.SysRoleMenu;
import com.baomidou.mybatisplus.service.IService;

import java.util.Collection;

/**
 * <p>
 * 角色菜单表 服务类
 * </p>
 *
 * @author zscat
 * @since 2017-10-29
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {

    /**
     * 更新角色菜单
     *
     *
     * @param role
     * @param roleId  角色
     * @param menuIds 菜单列表
     * @return
     */
    Boolean insertRoleMenus(String role, Integer roleId, Collection<Integer> menuIds);
}
