package com.ssk.haoke.cloud.server.house.api.query;

import com.ssk.haoke.cloud.server.house.api.dto.response.HouseInspectionRespDto;
import com.ssk.haoke.cloud.server.house.rest.RestResponse;
import com.ssk.haoke.cloud.server.house.eo.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
* 看房请求表服务接口
*
* @author 代码生成器
*/
@Api(tags = "好客租房管理平台：房源中心")
@FeignClient(name = "${haoke.manage.center.resources.api.name:haoke-manage-center-resources}",
        path = "/v1/houseinspectionreq",
        url = "${haoke.manage.center.resources.api:}")
public interface IHouseInspectionReqQueryApi {

    /**
    * 根据id查询看房请求表
    *
    * @param id 看房请求表id
    * @return   看房请求表数据
    */
    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询看房请求表", notes = "根据id查询看房请求表")
    RestResponse<HouseInspectionRespDto> queryById(@PathVariable("id") Long id);

    /**
    * 看房请求表分页数据
    *
    * @param filter   看房请求表查询条件
    * @param pageNum  当前页
    * @param pageSize 页大小
    * @return 看房请求表分页数据
    */
    @GetMapping("/page")
    @ApiOperation(value = "看房请求表分页数据", notes = "根据filter查询条件查询看房请求表数据，filter=HouseInspectionReqReqDto")
    RestResponse<PageInfo<HouseInspectionRespDto>> queryByPage(@RequestParam("filter") String filter,
                                                   @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                                   @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize);

}
