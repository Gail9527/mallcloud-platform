package com.mallplus.marking.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.annotation.SysLog;
import com.mallplus.marking.entity.SmsFlashPromotionProductRelation;
import com.mallplus.marking.service.ISmsFlashPromotionProductRelationService;
import com.mallplus.marking.vo.SmsFlashPromotionSessionDetail;
import com.central.common.utils.CommonResult;
import com.central.common.utils.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 商品限时购与商品关系表
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Slf4j
@RestController
@Api(tags = "SmsFlashPromotionProductRelationController", description = "商品限时购与商品关系表管理")
@RequestMapping("/marking/SmsFlashPromotionProductRelation")
public class SmsFlashPromotionProductRelationController {
    @Resource
    private ISmsFlashPromotionProductRelationService ISmsFlashPromotionProductRelationService;

    @SysLog(MODULE = "marking", REMARK = "根据条件查询所有商品限时购与商品关系表列表")
    @ApiOperation("根据条件查询所有商品限时购与商品关系表列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('marking:SmsFlashPromotionProductRelation:read')")
    public Object getSmsFlashPromotionProductRelationByPage(SmsFlashPromotionProductRelation entity,
                                                            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize
    ) {
        try {
            return new CommonResult().success(ISmsFlashPromotionProductRelationService.page(new Page<SmsFlashPromotionProductRelation>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有商品限时购与商品关系表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "sms", REMARK = "批量选择商品添加关联")
    @ApiOperation("批量选择商品添加关联")
    @RequestMapping(value = "/batchCreate", method = RequestMethod.POST)
    @ResponseBody
    public Object create(@RequestBody List<SmsFlashPromotionProductRelation> relationList) {
        boolean count = ISmsFlashPromotionProductRelationService.saveBatch(relationList);
        if (count) {
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "marking", REMARK = "保存商品限时购与商品关系表")
    @ApiOperation("保存商品限时购与商品关系表")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('marking:SmsFlashPromotionProductRelation:create')")
    public Object saveSmsFlashPromotionProductRelation(@RequestBody SmsFlashPromotionProductRelation entity) {
        try {
            if (ISmsFlashPromotionProductRelationService.save(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存商品限时购与商品关系表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "marking", REMARK = "更新商品限时购与商品关系表")
    @ApiOperation("更新商品限时购与商品关系表")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('marking:SmsFlashPromotionProductRelation:update')")
    public Object updateSmsFlashPromotionProductRelation(@RequestBody SmsFlashPromotionProductRelation entity) {
        try {
            if (ISmsFlashPromotionProductRelationService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新商品限时购与商品关系表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "marking", REMARK = "删除商品限时购与商品关系表")
    @ApiOperation("删除商品限时购与商品关系表")
    @DeleteMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('marking:SmsFlashPromotionProductRelation:delete')")
    public Object deleteSmsFlashPromotionProductRelation(@ApiParam("商品限时购与商品关系表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("商品限时购与商品关系表id");
            }
            if (ISmsFlashPromotionProductRelationService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除商品限时购与商品关系表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "marking", REMARK = "给商品限时购与商品关系表分配商品限时购与商品关系表")
    @ApiOperation("查询商品限时购与商品关系表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('marking:SmsFlashPromotionProductRelation:read')")
    public Object getSmsFlashPromotionProductRelationById(@ApiParam("商品限时购与商品关系表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("商品限时购与商品关系表id");
            }
            SmsFlashPromotionProductRelation coupon = ISmsFlashPromotionProductRelationService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询商品限时购与商品关系表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }

    @ApiOperation(value = "批量删除商品限时购与商品关系表")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除商品限时购与商品关系表")
    @PreAuthorize("hasAuthority('marking:SmsFlashPromotionProductRelation:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = ISmsFlashPromotionProductRelationService.removeByIds(ids);
        if (count) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }


}
