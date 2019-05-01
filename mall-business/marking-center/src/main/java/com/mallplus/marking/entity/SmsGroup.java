package com.mallplus.marking.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@TableName("sms_group")
public class SmsGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("goods_id")
    private Long goodsId;

    /**
     * 商品
     */
    @TableField("goods_name")
    private String goodsName;

    /**
     * 商品价格
     */
    @TableField("origin_price")
    private BigDecimal originPrice;

    /**
     * 拼团价格
     */
    @TableField("group_price")
    private BigDecimal groupPrice;

    /**
     * 开始时间
     */
    @TableField("start_time")
    private Date startTime;

    /**
     * 结束时间
     */
    @TableField("end_time")
    private Date endTime;

    /**
     * 拼团小时
     */
    private Integer hours;

    /**
     * 成团人数
     */
    private Integer peoples;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 拼团总人数
     */
    @TableField("max_people")
    private Integer maxPeople;

    /**
     * 团购次数
     */
    @TableField("limit_goods")
    private Integer limitGoods;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public BigDecimal getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(BigDecimal originPrice) {
        this.originPrice = originPrice;
    }

    public BigDecimal getGroupPrice() {
        return groupPrice;
    }

    public void setGroupPrice(BigDecimal groupPrice) {
        this.groupPrice = groupPrice;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Integer getPeoples() {
        return peoples;
    }

    public void setPeoples(Integer peoples) {
        this.peoples = peoples;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(Integer maxPeople) {
        this.maxPeople = maxPeople;
    }

    public Integer getLimitGoods() {
        return limitGoods;
    }

    public void setLimitGoods(Integer limitGoods) {
        this.limitGoods = limitGoods;
    }

    @Override
    public String toString() {
        return "SmsGroup{" +
        ", id=" + id +
        ", goodsId=" + goodsId +
        ", goodsName=" + goodsName +
        ", originPrice=" + originPrice +
        ", groupPrice=" + groupPrice +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        ", hours=" + hours +
        ", peoples=" + peoples +
        ", status=" + status +
        ", createTime=" + createTime +
        ", maxPeople=" + maxPeople +
        ", limitGoods=" + limitGoods +
        "}";
    }
}
