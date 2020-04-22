package com.ssk.haoke.cloud.server.house.api.query;

import com.ssk.haoke.cloud.server.house.api.dto.response.DropDownRespDto;
import com.ssk.haoke.cloud.server.house.api.dto.response.HouseResourcesListRespDto;
import com.ssk.haoke.cloud.server.house.api.dto.response.HouseResourcesRespDto;
import com.ssk.haoke.cloud.server.house.eo.PageInfo;
import com.ssk.haoke.cloud.server.house.rest.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@FeignClient(name = "${haoke.manage.center.resources.api.name:haoke-manage-center-resources}",
        path = "/v1/house/resources",
        url = "${haoke.manage.center.resources.api:}")
@Api(tags = "好客租房管理平台：房源中心")
public interface IHouseResourcesQueryApi {
    /**
     * 分页查询房源列表
     * *
     * @param pageNum 当前页
     * @param pageSize 页面大小
     * @return
     */
    @GetMapping(produces = {"application/json"})
    @ApiOperation(value = "分页查询房源",notes = "分页查询房源")
    RestResponse<PageInfo<HouseResourcesListRespDto>> queryHouseResourcesList(@RequestParam("filter") String filter,
                                                                              @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                                                              @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize);

    /**
     * 根据id删除房源
     * @param id
     * @return
     */
    @GetMapping("{id}")
    @ResponseBody
    @ApiOperation(value = "根据id查询房源",notes = "根据id查询房源")
    RestResponse<HouseResourcesRespDto> queryHouseResourcesById(@PathVariable("id") Long id);

    @GetMapping("cityName/{cityName}")
    @ResponseBody
    @ApiOperation(value = "根据城市查询房源",notes = "根据城市查询房源")
    RestResponse<PageInfo<HouseResourcesRespDto>> getPageByCity(@PathVariable("cityName")String cityName,
                                                                @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                                                @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize);

    @GetMapping("allCity")
    @ResponseBody
    @ApiOperation(value = "查询城市下拉列表",notes = "查询城市下拉列表")
    RestResponse<List<DropDownRespDto>> getAllCity();

}
