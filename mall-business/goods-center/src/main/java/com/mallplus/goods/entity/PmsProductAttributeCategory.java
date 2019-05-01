package com.mallplus.goods.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 产品属性分类表
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Data
@TableName("pms_product_attribute_category")
public class PmsProductAttributeCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    /**
     * 属性数量
     */
    @TableField("attribute_count")
    private Integer attributeCount;

    /**
     * 参数数量
     */
    @TableField("param_count")
    private Integer paramCount;
    @TableField(exist = false)
    List<PmsProduct> goodsList;


}
