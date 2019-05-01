package com.central.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.central.common.model.SysRoleUser;
import com.central.user.mapper.SysUserRoleMapper;
import com.central.user.service.ISysUserRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台用户和角色关系表 服务实现类
 * </p>
 *
 * @author zscat
 * @since 2019-04-14
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysRoleUser> implements ISysUserRoleService {

}
