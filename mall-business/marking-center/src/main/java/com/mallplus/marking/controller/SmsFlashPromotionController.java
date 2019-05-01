package com.mallplus.marking.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.annotation.SysLog;
import com.mallplus.marking.entity.SmsFlashPromotion;
import com.mallplus.marking.service.ISmsFlashPromotionService;
import com.central.common.utils.CommonResult;
import com.central.common.utils.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 限时购表
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Slf4j
@RestController
@Api(tags = "SmsFlashPromotionController", description = "限时购表管理")
@RequestMapping("/marking/SmsFlashPromotion")
public class SmsFlashPromotionController {
    @Resource
    private ISmsFlashPromotionService ISmsFlashPromotionService;

    @SysLog(MODULE = "marking", REMARK = "根据条件查询所有限时购表列表")
    @ApiOperation("根据条件查询所有限时购表列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('marking:SmsFlashPromotion:read')")
    public Object getSmsFlashPromotionByPage(SmsFlashPromotion entity,
                                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                             @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize
    ) {
        try {
            return new CommonResult().success(ISmsFlashPromotionService.page(new Page<SmsFlashPromotion>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有限时购表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "marking", REMARK = "保存限时购表")
    @ApiOperation("保存限时购表")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('marking:SmsFlashPromotion:create')")
    public Object saveSmsFlashPromotion(@RequestBody SmsFlashPromotion entity) {
        try {
            entity.setCreateTime(new Date());
            if (ISmsFlashPromotionService.save(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存限时购表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "marking", REMARK = "更新限时购表")
    @ApiOperation("更新限时购表")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('marking:SmsFlashPromotion:update')")
    public Object updateSmsFlashPromotion(@RequestBody SmsFlashPromotion entity) {
        try {
            if (ISmsFlashPromotionService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新限时购表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "marking", REMARK = "删除限时购表")
    @ApiOperation("删除限时购表")
    @DeleteMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('marking:SmsFlashPromotion:delete')")
    public Object deleteSmsFlashPromotion(@ApiParam("限时购表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("限时购表id");
            }
            if (ISmsFlashPromotionService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除限时购表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "marking", REMARK = "给限时购表分配限时购表")
    @ApiOperation("查询限时购表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('marking:SmsFlashPromotion:read')")
    public Object getSmsFlashPromotionById(@ApiParam("限时购表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("限时购表id");
            }
            SmsFlashPromotion coupon = ISmsFlashPromotionService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询限时购表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }

    @ApiOperation(value = "批量删除限时购表")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除限时购表")
    @PreAuthorize("hasAuthority('marking:SmsFlashPromotion:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = ISmsFlashPromotionService.removeByIds(ids);
        if (count) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

    @SysLog(MODULE = "sms", REMARK = "修改上下线状态")
    @ApiOperation("修改上下线状态")
    @RequestMapping(value = "/update/status/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable Long id, Integer status) {
        int count = ISmsFlashPromotionService.updateStatus(id, status);
        if (count > 0) {
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }
}
