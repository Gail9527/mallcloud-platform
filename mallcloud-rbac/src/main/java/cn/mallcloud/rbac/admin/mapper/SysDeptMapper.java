package cn.mallcloud.rbac.admin.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import cn.mallcloud.rbac.admin.model.entity.SysDept;

import java.util.List;

/**
 * <p>
 * 部门管理 Mapper 接口
 * </p>
 *
 * @author zscat
 * @since 2018-01-20
 */
public interface SysDeptMapper extends BaseMapper<SysDept> {

    /**
     * 关联dept——relation
     *
     * @param delFlag 删除标记
     * @return 数据列表
     */
    List<SysDept> selectDeptDtoList(String delFlag);

    /**
     * 删除部门关系表数据
     * @param id 部门ID
     */
    void deleteDeptRealtion(Integer id);
}
