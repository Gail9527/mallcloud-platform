package com.mallplus.member.controller;

import com.central.common.utils.CommonResult;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.mallplus.member.entity.UmsMemberReceiveAddress;
import com.mallplus.member.service.IUmsMemberReceiveAddressService;
import com.central.common.utils.ValidatorUtils;
import com.central.common.annotation.SysLog;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 会员收货地址表
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Slf4j
@RestController
@Api(tags = "UmsMemberReceiveAddressController", description = "会员收货地址表管理")
@RequestMapping("/ums/UmsMemberReceiveAddress")
public class UmsMemberReceiveAddressController {
    @Resource
    private IUmsMemberReceiveAddressService IUmsMemberReceiveAddressService;

    @SysLog(MODULE = "ums", REMARK = "根据条件查询所有会员收货地址表列表")
    @ApiOperation("根据条件查询所有会员收货地址表列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('ums:UmsMemberReceiveAddress:read')")
    public Object getUmsMemberReceiveAddressByPage(UmsMemberReceiveAddress entity,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize
    ) {
        try {
            return new CommonResult().success(IUmsMemberReceiveAddressService.page(new Page<UmsMemberReceiveAddress>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有会员收货地址表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "ums", REMARK = "保存会员收货地址表")
    @ApiOperation("保存会员收货地址表")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('ums:UmsMemberReceiveAddress:create')")
    public Object saveUmsMemberReceiveAddress(@RequestBody UmsMemberReceiveAddress entity) {
        try {
            if (IUmsMemberReceiveAddressService.save(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存会员收货地址表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "ums", REMARK = "更新会员收货地址表")
    @ApiOperation("更新会员收货地址表")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('ums:UmsMemberReceiveAddress:update')")
    public Object updateUmsMemberReceiveAddress(@RequestBody UmsMemberReceiveAddress entity) {
        try {
            if (IUmsMemberReceiveAddressService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新会员收货地址表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "ums", REMARK = "删除会员收货地址表")
    @ApiOperation("删除会员收货地址表")
    @DeleteMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('ums:UmsMemberReceiveAddress:delete')")
    public Object deleteUmsMemberReceiveAddress(@ApiParam("会员收货地址表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("会员收货地址表id");
            }
            if (IUmsMemberReceiveAddressService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除会员收货地址表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "ums", REMARK = "给会员收货地址表分配会员收货地址表")
    @ApiOperation("查询会员收货地址表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('ums:UmsMemberReceiveAddress:read')")
    public Object getUmsMemberReceiveAddressById(@ApiParam("会员收货地址表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("会员收货地址表id");
            }
            UmsMemberReceiveAddress coupon = IUmsMemberReceiveAddressService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询会员收货地址表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }

    @ApiOperation(value = "批量删除会员收货地址表")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除会员收货地址表")
    @PreAuthorize("hasAuthority('ums:UmsMemberReceiveAddress:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = IUmsMemberReceiveAddressService.removeByIds(ids);
        if (count) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

}
