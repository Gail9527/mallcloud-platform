package cn.mallcloud.rbac.admin.model.dto;

import cn.mallcloud.rbac.admin.model.entity.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author zscat
 * @date 2017/11/5
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends SysUser {
    /**
     * 角色ID
     */
    private List<Integer> role;

    private Integer deptId;

    /**
     * 新密码
     */
    private String newpassword1;
}
