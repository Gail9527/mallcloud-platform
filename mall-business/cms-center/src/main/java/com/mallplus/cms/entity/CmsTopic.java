package com.mallplus.cms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 话题表
 * </p>
 *
 * @author zscat
 * @since 2019-04-17
 */
@TableName("cms_topic")
public class CmsTopic implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 所属分类
     */
    @TableField("category_id")
    private Long categoryId;

    /**
     * 标题
     */
    private String name;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

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
     * 参与人数
     */
    @TableField("attend_count")
    private Integer attendCount;

    /**
     * 关注人数
     */
    @TableField("attention_count")
    private Integer attentionCount;

    /**
     * 点击人数
     */
    @TableField("read_count")
    private Integer readCount;

    /**
     * 奖品名称
     */
    @TableField("award_name")
    private String awardName;

    /**
     * 参与方式
     */
    @TableField("attend_type")
    private String attendType;

    /**
     * 话题内容
     */
    private String content;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public Integer getAttendCount() {
        return attendCount;
    }

    public void setAttendCount(Integer attendCount) {
        this.attendCount = attendCount;
    }

    public Integer getAttentionCount() {
        return attentionCount;
    }

    public void setAttentionCount(Integer attentionCount) {
        this.attentionCount = attentionCount;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    public String getAttendType() {
        return attendType;
    }

    public void setAttendType(String attendType) {
        this.attendType = attendType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CmsTopic{" +
        ", id=" + id +
        ", categoryId=" + categoryId +
        ", name=" + name +
        ", createTime=" + createTime +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        ", attendCount=" + attendCount +
        ", attentionCount=" + attentionCount +
        ", readCount=" + readCount +
        ", awardName=" + awardName +
        ", attendType=" + attendType +
        ", content=" + content +
        "}";
    }
}
