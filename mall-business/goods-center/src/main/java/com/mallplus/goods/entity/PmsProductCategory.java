package com.mallplus.goods.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 产品分类
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@TableName("pms_product_category")
@Data
public class PmsProductCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 上机分类的编号：0表示一级分类
     */
    @TableField("parent_id")
    private Long parentId;

    private String name;

    /**
     * 分类级别：0->1级；1->2级
     */
    private Integer level;

    @TableField("product_count")
    private Integer productCount;

    @TableField("product_unit")
    private String productUnit;

    /**
     * 是否显示在导航栏：0->不显示；1->显示
     */
    @TableField("nav_status")
    private Integer navStatus;

    /**
     * 显示状态：0->不显示；1->显示
     */
    @TableField("show_status")
    private Integer showStatus;

    private Integer sort;

    /**
     * 图标
     */
    private String icon;

    private String keywords;

    /**
     * 描述
     */
    private String description;

    @TableField(exist = false)
    @ApiModelProperty("产品相关筛选属性集合")
    private List<Long> productAttributeIdList;


}
