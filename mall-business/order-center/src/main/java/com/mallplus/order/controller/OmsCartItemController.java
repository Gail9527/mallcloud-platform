package com.mallplus.order.controller;

import com.central.common.utils.CommonResult;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.mallplus.order.entity.OmsCartItem;
import com.mallplus.order.service.IOmsCartItemService;
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
 * 购物车表
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
@Slf4j
@RestController
@Api(tags = "OmsCartItemController", description = "购物车表管理")
@RequestMapping("/oms/OmsCartItem")
public class OmsCartItemController {
    @Resource
    private IOmsCartItemService IOmsCartItemService;

    @SysLog(MODULE = "oms", REMARK = "根据条件查询所有购物车表列表")
    @ApiOperation("根据条件查询所有购物车表列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('oms:OmsCartItem:read')")
    public Object getOmsCartItemByPage(OmsCartItem entity,
                                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize
    ) {
        try {
            return new CommonResult().success(IOmsCartItemService.page(new Page<OmsCartItem>(pageNum, pageSize), new QueryWrapper<>(entity)));
        } catch (Exception e) {
            log.error("根据条件查询所有购物车表列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "oms", REMARK = "保存购物车表")
    @ApiOperation("保存购物车表")
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('oms:OmsCartItem:create')")
    public Object saveOmsCartItem(@RequestBody OmsCartItem entity) {
        try {
            if (IOmsCartItemService.save(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("保存购物车表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "oms", REMARK = "更新购物车表")
    @ApiOperation("更新购物车表")
    @PostMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('oms:OmsCartItem:update')")
    public Object updateOmsCartItem(@RequestBody OmsCartItem entity) {
        try {
            if (IOmsCartItemService.updateById(entity)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("更新购物车表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "oms", REMARK = "删除购物车表")
    @ApiOperation("删除购物车表")
    @DeleteMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('oms:OmsCartItem:delete')")
    public Object deleteOmsCartItem(@ApiParam("购物车表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("购物车表id");
            }
            if (IOmsCartItemService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除购物车表：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "oms", REMARK = "给购物车表分配购物车表")
    @ApiOperation("查询购物车表明细")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('oms:OmsCartItem:read')")
    public Object getOmsCartItemById(@ApiParam("购物车表id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("购物车表id");
            }
            OmsCartItem coupon = IOmsCartItemService.getById(id);
            return new CommonResult().success(coupon);
        } catch (Exception e) {
            log.error("查询购物车表明细：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }

    }

    @ApiOperation(value = "批量删除购物车表")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    @SysLog(MODULE = "pms", REMARK = "批量删除购物车表")
    @PreAuthorize("hasAuthority('oms:OmsCartItem:delete')")
    public Object deleteBatch(@RequestParam("ids") List<Long> ids) {
        boolean count = IOmsCartItemService.removeByIds(ids);
        if (count) {
            return new CommonResult().success(count);
        } else {
            return new CommonResult().failed();
        }
    }

}
