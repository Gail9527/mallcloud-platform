package cn.mallcloud.rbac.admin.model.dto;

import cn.mallcloud.rbac.admin.model.entity.SysRole;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zscat
 * @date 2018/1/20
 * 角色Dto
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleDTO extends SysRole {
    /**
     * 角色部门Id
     */
    private Integer roleDeptId;

    /**
     * 部门名称
     */
    private String deptName;
}
