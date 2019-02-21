package cn.mallcloud.rbac.admin.service.impl;

import cn.mallcloud.rbac.admin.mapper.SysRoleMenuMapper;
import cn.mallcloud.rbac.admin.model.entity.SysRoleMenu;
import cn.mallcloud.rbac.admin.service.SysRoleMenuService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 角色菜单表 服务实现类
 * </p>
 *
 * @author zscat
 * @since 2017-10-29
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {
    @Override
    public Boolean insertRoleMenus(String role, Integer roleId, Collection<Integer> menuIds) {
        SysRoleMenu condition = new SysRoleMenu();
        condition.setRoleId(roleId);
        this.delete(new EntityWrapper<>(condition));

        List<SysRoleMenu> roleMenuList = new ArrayList<>();
        for (Integer menuId : menuIds) {
            SysRoleMenu roleMenu = new SysRoleMenu();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuId);
            roleMenuList.add(roleMenu);
        }
        return this.insertBatch(roleMenuList);
    }
}
