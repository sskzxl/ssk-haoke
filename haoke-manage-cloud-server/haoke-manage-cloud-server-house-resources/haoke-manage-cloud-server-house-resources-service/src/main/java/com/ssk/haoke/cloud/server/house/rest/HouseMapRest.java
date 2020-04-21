package com.ssk.haoke.cloud.server.house.rest;

import com.ssk.haoke.cloud.server.house.api.IHouseMapApi;
import com.ssk.haoke.cloud.server.house.api.vo.map.MapHouseDataResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("/v1/house")
@RestController
public class HouseMapRest implements IHouseMapApi{
    @Resource
    private IHouseMapApi houseMapApi;
    @Override
    public RestResponse<MapHouseDataResult> getMap(Float lng,Float lat,Integer zoom){
        return houseMapApi.getMap(lng, lat, zoom);
    }
}
