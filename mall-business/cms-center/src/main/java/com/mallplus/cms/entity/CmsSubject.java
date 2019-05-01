package com.mallplus.cms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 专题表
 * </p>
 *
 * @author zscat
 * @since 2019-04-17
 */
@TableName("cms_subject")
public class CmsSubject implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 分类
     */
    @TableField("category_id")
    private Long categoryId;

    /**
     * 标题
     */
    private String title;

    /**
     * 专题主图
     */
    private String pic;

    /**
     * 关联产品数量
     */
    @TableField("product_count")
    private Integer productCount;

    /**
     * 推荐
     */
    @TableField("recommend_status")
    private Integer recommendStatus;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 收藏量
     */
    @TableField("collect_count")
    private Integer collectCount;

    /**
     * 点击量
     */
    @TableField("read_count")
    private Integer readCount;

    /**
     * 评论量
     */
    @TableField("comment_count")
    private Integer commentCount;

    /**
     * 画册图片用逗号分割
     */
    @TableField("album_pics")
    private String albumPics;

    /**
     * 描述
     */
    private String description;

    /**
     * 显示状态：0->不显示；1->显示
     */
    @TableField("show_status")
    private Integer showStatus;

    /**
     * 内容
     */
    private String content;

    /**
     * 转发数
     */
    @TableField("forward_count")
    private Integer forwardCount;

    /**
     * 专题分类名称
     */
    @TableField("category_name")
    private String categoryName;

    @TableField("area_id")
    private Long areaId;

    @TableField("school_id")
    private Long schoolId;

    @TableField("member_id")
    private Long memberId;

    /**
     * 打赏
     */
    private Integer reward;


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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public Integer getRecommendStatus() {
        return recommendStatus;
    }

    public void setRecommendStatus(Integer recommendStatus) {
        this.recommendStatus = recommendStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(Integer collectCount) {
        this.collectCount = collectCount;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public String getAlbumPics() {
        return albumPics;
    }

    public void setAlbumPics(String albumPics) {
        this.albumPics = albumPics;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getForwardCount() {
        return forwardCount;
    }

    public void setForwardCount(Integer forwardCount) {
        this.forwardCount = forwardCount;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Integer getReward() {
        return reward;
    }

    public void setReward(Integer reward) {
        this.reward = reward;
    }

    @Override
    public String toString() {
        return "CmsSubject{" +
        ", id=" + id +
        ", categoryId=" + categoryId +
        ", title=" + title +
        ", pic=" + pic +
        ", productCount=" + productCount +
        ", recommendStatus=" + recommendStatus +
        ", createTime=" + createTime +
        ", collectCount=" + collectCount +
        ", readCount=" + readCount +
        ", commentCount=" + commentCount +
        ", albumPics=" + albumPics +
        ", description=" + description +
        ", showStatus=" + showStatus +
        ", content=" + content +
        ", forwardCount=" + forwardCount +
        ", categoryName=" + categoryName +
        ", areaId=" + areaId +
        ", schoolId=" + schoolId +
        ", memberId=" + memberId +
        ", reward=" + reward +
        "}";
    }
}
