package cn.mallcloud.admin.web;

import cn.mallcloud.admin.registry.store.ApplicationStore;
import cn.mallcloud.common.web.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * 服务治理server端 注册Controller
 *
 * @author zscat
 * @date 2017/11/20 11:52
 */
@RestController
@RequestMapping("/api/applications")
@Api(tags = "服务治理接口", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApplicationController {

    @Autowired
    private ApplicationStore applicationStore;

    /**
     * 查询所有服务
     *
     * @return List
     */
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "查询服务列表", notes = "根据指定参数获取服务列表")
    public Response applications() {
        return Response.success(applicationStore.findAll());
    }

    /**
     * 获取服务信息详情
     *
     * @param instanceId 服务instanceId
     * @return List
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ApiOperation(value = "查询服务详情", notes = "根据instanceId获取服务详细信息")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "instanceId", value = "instanceId", dataType = "String", required = true, paramType = "query"),
    })
    public Response getDetail(@RequestParam(value = "instanceId") String instanceId) {
        return Response.success(applicationStore.find(instanceId));
    }

    /**
     * 移除app
     *
     * @return Response
     */
    @RequestMapping(value = "/{instanceId}", method = RequestMethod.DELETE)
    @ApiOperation(value = "app统计信息", notes = "获取所有app统计信息")
    public Response delete(@PathVariable("instanceId") String instanceId) {
        applicationStore.delete(instanceId);
        return Response.success();
    }
}
