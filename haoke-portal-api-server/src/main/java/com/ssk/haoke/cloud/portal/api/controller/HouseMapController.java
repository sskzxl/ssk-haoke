package com.ssk.haoke.cloud.portal.api.controller;

import com.ssk.haoke.cloud.portal.api.service.impl.HouseMapService;
import com.ssk.haoke.cloud.server.house.api.vo.map.MapHouseDataResult;
import com.ssk.haoke.cloud.server.house.rest.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/house")
@Api(tags = "地图找房接口")
@RestController
public class HouseMapController {
    @Autowired
    private HouseMapService houseMapService;

    @GetMapping("/map")
    @ApiOperation(value = "地图找房",notes = "输入地图中心点经纬度和缩放比例，输出附近点数据")
    private RestResponse<MapHouseDataResult> getMap(@RequestParam("lng") Float lng,
                                                    @RequestParam("lat")Float lat,
                                                    @RequestParam("zoom")Integer zoom){
       return houseMapService.getMap(lng, lat, zoom);
    }

}
