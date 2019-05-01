package com.central.common.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zscat
 * @since 2019-05-01
 */
@Data
@TableName("sys_role_user")
public class SysRoleUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("user_id")
    private Long userId;

    @TableField("role_id")
    private Long roleId;



    @Override
    public String toString() {
        return "SysRoleUser{" +
        ", userId=" + userId +
        ", roleId=" + roleId +
        "}";
    }
}
