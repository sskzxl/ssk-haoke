package com.ssk.haoke.cloud.server.house.api;

import com.ssk.haoke.cloud.server.house.api.vo.map.MapHouseDataResult;
import com.ssk.haoke.cloud.server.house.rest.RestResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
@FeignClient(name = "${haoke.manage.center.resources.api.name:haoke-manage-center-resources}",
        path = "/v1/house", url = "${haoke.manage.center.resources.api:}")
public interface IHouseMapApi {
    @GetMapping("map")
    @ApiOperation(value = "地图找房",notes = "输入地图中心点经纬度和缩放比例，输出附近点数据")
    RestResponse<MapHouseDataResult> getMap(@RequestParam("lng") Float lng,
                                                    @RequestParam("lat")Float lat,
                                                    @RequestParam("zoom")Integer zoom);
}
