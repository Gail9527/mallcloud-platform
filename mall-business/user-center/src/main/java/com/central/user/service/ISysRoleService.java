package com.central.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.central.common.model.SysRole;
import com.central.common.model.SysRoleMenu;

import java.util.List;

/**
* @author mall
 */
public interface ISysRoleService extends IService<SysRole> {
	public List<SysRoleMenu> getRolePermission(Long roleId);

	boolean saves(SysRole entity);

	boolean updates(SysRole entity);
}
