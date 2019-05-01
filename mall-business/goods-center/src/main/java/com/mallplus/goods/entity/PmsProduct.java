package com.mallplus.goods.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 商品信息
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@TableName("pms_product")
public class PmsProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("brand_id")
    private Long brandId;

    @TableField("product_category_id")
    private Long productCategoryId;

    @TableField("feight_template_id")
    private Long feightTemplateId;

    @TableField("product_attribute_category_id")
    private Long productAttributeCategoryId;

    private String name;

    private String pic;

    /**
     * 货号
     */
    @TableField("product_sn")
    private String productSn;

    /**
     * 删除状态：0->未删除；1->已删除
     */
    @TableField("delete_status")
    private Integer deleteStatus;

    /**
     * 上架状态：0->下架；1->上架
     */
    @TableField("publish_status")
    private Integer publishStatus;

    /**
     * 新品状态:0->不是新品；1->新品
     */
    @TableField("new_status")
    private Integer newStatus;

    /**
     * 推荐状态；0->不推荐；1->推荐
     */
    @TableField("recommand_status")
    private Integer recommandStatus;

    /**
     * 审核状态：0->未审核；1->审核通过
     */
    @TableField("verify_status")
    private Integer verifyStatus;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 销量
     */
    private Integer sale;

    private BigDecimal price;

    /**
     * 促销价格
     */
    @TableField("promotion_price")
    private BigDecimal promotionPrice;

    /**
     * 赠送的成长值
     */
    @TableField("gift_growth")
    private Integer giftGrowth;

    /**
     * 赠送的积分
     */
    @TableField("gift_point")
    private Integer giftPoint;

    /**
     * 限制使用的积分数
     */
    @TableField("use_point_limit")
    private Integer usePointLimit;

    /**
     * 副标题
     */
    @TableField("sub_title")
    private String subTitle;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 市场价
     */
    @TableField("original_price")
    private BigDecimal originalPrice;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 库存预警值
     */
    @TableField("low_stock")
    private Integer lowStock;

    /**
     * 单位
     */
    private String unit;

    /**
     * 商品重量，默认为克
     */
    private BigDecimal weight;

    /**
     * 是否为预告商品：0->不是；1->是
     */
    @TableField("preview_status")
    private Integer previewStatus;

    /**
     * 以逗号分割的产品服务：1->无忧退货；2->快速退款；3->免费包邮
     */
    @TableField("service_ids")
    private String serviceIds;

    private String keywords;

    private String note;

    /**
     * 画册图片，连产品图片限制为5张，以逗号分割
     */
    @TableField("album_pics")
    private String albumPics;

    @TableField("detail_title")
    private String detailTitle;

    @TableField("detail_desc")
    private String detailDesc;

    /**
     * 产品详情网页内容
     */
    @TableField("detail_html")
    private String detailHtml;

    /**
     * 移动端网页详情
     */
    @TableField("detail_mobile_html")
    private String detailMobileHtml;

    /**
     * 促销开始时间
     */
    @TableField("promotion_start_time")
    private Date promotionStartTime;

    /**
     * 促销结束时间
     */
    @TableField("promotion_end_time")
    private Date promotionEndTime;

    /**
     * 活动限购数量
     */
    @TableField("promotion_per_limit")
    private Integer promotionPerLimit;

    /**
     * 促销类型：0->没有促销使用原价;1->使用促销价；2->使用会员价；3->使用阶梯价格；4->使用满减价格；5->限时购
     */
    @TableField("promotion_type")
    private Integer promotionType;

    /**
     * 品牌名称
     */
    @TableField("brand_name")
    private String brandName;

    /**
     * 商品分类名称
     */
    @TableField("product_category_name")
    private String productCategoryName;

    @TableField("supply_id")
    private Long supplyId;

    @TableField("create_time")
    private Date createTime;

    @TableField("school_id")
    private Long schoolId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public Long getFeightTemplateId() {
        return feightTemplateId;
    }

    public void setFeightTemplateId(Long feightTemplateId) {
        this.feightTemplateId = feightTemplateId;
    }

    public Long getProductAttributeCategoryId() {
        return productAttributeCategoryId;
    }

    public void setProductAttributeCategoryId(Long productAttributeCategoryId) {
        this.productAttributeCategoryId = productAttributeCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getProductSn() {
        return productSn;
    }

    public void setProductSn(String productSn) {
        this.productSn = productSn;
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public Integer getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(Integer publishStatus) {
        this.publishStatus = publishStatus;
    }

    public Integer getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(Integer newStatus) {
        this.newStatus = newStatus;
    }

    public Integer getRecommandStatus() {
        return recommandStatus;
    }

    public void setRecommandStatus(Integer recommandStatus) {
        this.recommandStatus = recommandStatus;
    }

    public Integer getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(Integer verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getSale() {
        return sale;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(BigDecimal promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public Integer getGiftGrowth() {
        return giftGrowth;
    }

    public void setGiftGrowth(Integer giftGrowth) {
        this.giftGrowth = giftGrowth;
    }

    public Integer getGiftPoint() {
        return giftPoint;
    }

    public void setGiftPoint(Integer giftPoint) {
        this.giftPoint = giftPoint;
    }

    public Integer getUsePointLimit() {
        return usePointLimit;
    }

    public void setUsePointLimit(Integer usePointLimit) {
        this.usePointLimit = usePointLimit;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getLowStock() {
        return lowStock;
    }

    public void setLowStock(Integer lowStock) {
        this.lowStock = lowStock;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public Integer getPreviewStatus() {
        return previewStatus;
    }

    public void setPreviewStatus(Integer previewStatus) {
        this.previewStatus = previewStatus;
    }

    public String getServiceIds() {
        return serviceIds;
    }

    public void setServiceIds(String serviceIds) {
        this.serviceIds = serviceIds;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAlbumPics() {
        return albumPics;
    }

    public void setAlbumPics(String albumPics) {
        this.albumPics = albumPics;
    }

    public String getDetailTitle() {
        return detailTitle;
    }

    public void setDetailTitle(String detailTitle) {
        this.detailTitle = detailTitle;
    }

    public String getDetailDesc() {
        return detailDesc;
    }

    public void setDetailDesc(String detailDesc) {
        this.detailDesc = detailDesc;
    }

    public String getDetailHtml() {
        return detailHtml;
    }

    public void setDetailHtml(String detailHtml) {
        this.detailHtml = detailHtml;
    }

    public String getDetailMobileHtml() {
        return detailMobileHtml;
    }

    public void setDetailMobileHtml(String detailMobileHtml) {
        this.detailMobileHtml = detailMobileHtml;
    }

    public Date getPromotionStartTime() {
        return promotionStartTime;
    }

    public void setPromotionStartTime(Date promotionStartTime) {
        this.promotionStartTime = promotionStartTime;
    }

    public Date getPromotionEndTime() {
        return promotionEndTime;
    }

    public void setPromotionEndTime(Date promotionEndTime) {
        this.promotionEndTime = promotionEndTime;
    }

    public Integer getPromotionPerLimit() {
        return promotionPerLimit;
    }

    public void setPromotionPerLimit(Integer promotionPerLimit) {
        this.promotionPerLimit = promotionPerLimit;
    }

    public Integer getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(Integer promotionType) {
        this.promotionType = promotionType;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    public Long getSupplyId() {
        return supplyId;
    }

    public void setSupplyId(Long supplyId) {
        this.supplyId = supplyId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    @Override
    public String toString() {
        return "PmsProduct{" +
        ", id=" + id +
        ", brandId=" + brandId +
        ", productCategoryId=" + productCategoryId +
        ", feightTemplateId=" + feightTemplateId +
        ", productAttributeCategoryId=" + productAttributeCategoryId +
        ", name=" + name +
        ", pic=" + pic +
        ", productSn=" + productSn +
        ", deleteStatus=" + deleteStatus +
        ", publishStatus=" + publishStatus +
        ", newStatus=" + newStatus +
        ", recommandStatus=" + recommandStatus +
        ", verifyStatus=" + verifyStatus +
        ", sort=" + sort +
        ", sale=" + sale +
        ", price=" + price +
        ", promotionPrice=" + promotionPrice +
        ", giftGrowth=" + giftGrowth +
        ", giftPoint=" + giftPoint +
        ", usePointLimit=" + usePointLimit +
        ", subTitle=" + subTitle +
        ", description=" + description +
        ", originalPrice=" + originalPrice +
        ", stock=" + stock +
        ", lowStock=" + lowStock +
        ", unit=" + unit +
        ", weight=" + weight +
        ", previewStatus=" + previewStatus +
        ", serviceIds=" + serviceIds +
        ", keywords=" + keywords +
        ", note=" + note +
        ", albumPics=" + albumPics +
        ", detailTitle=" + detailTitle +
        ", detailDesc=" + detailDesc +
        ", detailHtml=" + detailHtml +
        ", detailMobileHtml=" + detailMobileHtml +
        ", promotionStartTime=" + promotionStartTime +
        ", promotionEndTime=" + promotionEndTime +
        ", promotionPerLimit=" + promotionPerLimit +
        ", promotionType=" + promotionType +
        ", brandName=" + brandName +
        ", productCategoryName=" + productCategoryName +
        ", supplyId=" + supplyId +
        ", createTime=" + createTime +
        ", schoolId=" + schoolId +
        "}";
    }
}
