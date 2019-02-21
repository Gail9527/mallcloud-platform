package cn.mallcloud.rbac.admin.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zscat
 * @date 2018/1/20
 * 部门树
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DeptTree extends TreeNode {
    private String name;
}
