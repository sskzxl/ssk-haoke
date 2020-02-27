package com.ssk.haoke.cloud.server.house.api;
import com.ssk.haoke.cloud.server.house.api.dto.request.HouseResourcesReqDto;
import com.ssk.haoke.cloud.server.house.rest.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "${haoke.manage.center.resources.api.name:haoke-manage-center-resources}",
        path = "/v1/house/resources", url = "${haoke.manage.center.resources.api:}")
@Api(tags = "好客租房管理平台：房源中心")
public interface IHouseResourcesApi {
    @ApiOperation(value = "删除房源",notes = "删除房源")
    @DeleteMapping("{id}")
    RestResponse<Boolean> deleteHouseResource(@PathVariable("id") Long id);

    /**
     * @param houseResourcesReqDto
     * @return -1:输入的参数不符合要求，0：数据插入数据库失败，1：成功
     */
    @PostMapping
    @ResponseBody
    @ApiOperation(value = "新增房源",notes = "新增房源")
    RestResponse<Integer>saveHouseResources(@RequestBody HouseResourcesReqDto houseResourcesReqDto);

    //更新房源
    @PutMapping(produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "更新房源",notes = "更新房源")
    RestResponse<Boolean> updateHouseResources(@RequestBody HouseResourcesReqDto houseResources);


}

