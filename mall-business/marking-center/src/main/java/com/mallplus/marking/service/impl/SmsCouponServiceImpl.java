package com.mallplus.marking.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mallplus.marking.entity.SmsCoupon;
import com.mallplus.marking.entity.SmsCouponProductCategoryRelation;
import com.mallplus.marking.entity.SmsCouponProductRelation;
import com.mallplus.marking.mapper.SmsCouponMapper;
import com.mallplus.marking.mapper.SmsCouponProductCategoryRelationMapper;
import com.mallplus.marking.mapper.SmsCouponProductRelationMapper;
import com.mallplus.marking.service.ISmsCouponProductCategoryRelationService;
import com.mallplus.marking.service.ISmsCouponProductRelationService;
import com.mallplus.marking.service.ISmsCouponService;
import com.mallplus.marking.vo.SmsCouponParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 优惠卷表 服务实现类
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Service
public class SmsCouponServiceImpl extends ServiceImpl<SmsCouponMapper, SmsCoupon> implements ISmsCouponService {

    @Resource
    private SmsCouponMapper couponMapper;
    @Resource
    private SmsCouponProductRelationMapper productRelationMapper;
    @Resource
    private SmsCouponProductCategoryRelationMapper productCategoryRelationMapper;
    @Resource
    private ISmsCouponProductRelationService productRelationDao;
    @Resource
    private ISmsCouponProductCategoryRelationService productCategoryRelationDao;

    @Override
    public boolean saves(SmsCouponParam couponParam) {
        couponParam.setCount(couponParam.getPublishCount());
        couponParam.setUseCount(0);
        couponParam.setReceiveCount(0);
        //插入优惠券表
        int count = couponMapper.insert(couponParam);
        //插入优惠券和商品关系表
        if (couponParam.getUseType().equals(2)) {
            for (SmsCouponProductRelation productRelation : couponParam.getProductRelationList()) {
                productRelation.setCouponId(couponParam.getId());
            }
            productRelationDao.saveBatch(couponParam.getProductRelationList());
        }
        //插入优惠券和商品分类关系表
        if (couponParam.getUseType().equals(1)) {
            for (SmsCouponProductCategoryRelation couponProductCategoryRelation : couponParam.getProductCategoryRelationList()) {
                couponProductCategoryRelation.setCouponId(couponParam.getId());
            }
            productCategoryRelationDao.saveBatch(couponParam.getProductCategoryRelationList());
        }
        return true;
    }

    @Override
    public boolean updateByIds(SmsCouponParam couponParam) {
        couponParam.setId(couponParam.getId());
        int count = couponMapper.updateById(couponParam);
        //删除后插入优惠券和商品关系表
        if (couponParam.getUseType().equals(2)) {
            for (SmsCouponProductRelation productRelation : couponParam.getProductRelationList()) {
                productRelation.setCouponId(couponParam.getId());
            }
            deleteProductRelation(couponParam.getId());
            productRelationDao.saveBatch(couponParam.getProductRelationList());
        }
        //删除后插入优惠券和商品分类关系表
        if (couponParam.getUseType().equals(1)) {
            for (SmsCouponProductCategoryRelation couponProductCategoryRelation : couponParam.getProductCategoryRelationList()) {
                couponProductCategoryRelation.setCouponId(couponParam.getId());
            }
            deleteProductCategoryRelation(couponParam.getId());
            productCategoryRelationDao.saveBatch(couponParam.getProductCategoryRelationList());
        }
        return true;
    }
    private void deleteProductCategoryRelation(Long id) {
        productCategoryRelationMapper.delete(new QueryWrapper<>(new SmsCouponProductCategoryRelation()).eq("coupon_id",id));
    }

    private void deleteProductRelation(Long id) {
        productRelationMapper.delete(new QueryWrapper<>(new SmsCouponProductRelation()).eq("coupon_id",id));
    }

    @Override
    public int delete(Long id) {
        //删除优惠券
        int count = couponMapper.deleteById(id);
        //删除商品关联
        deleteProductRelation(id);
        //删除商品分类关联
        deleteProductCategoryRelation(id);
        return count;
    }

    @Override
    public SmsCouponParam getItem(Long id) {
        return couponMapper.getItem(id);
    }
}
