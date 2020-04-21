package com.ssk.haoke.cloud.portal.api.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.ssk.haoke.cloud.server.house.api.IHouseMapApi;
import com.ssk.haoke.cloud.server.house.api.vo.map.MapHouseDataResult;
import com.ssk.haoke.cloud.server.house.rest.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service("houseMapService")
public class HouseMapService {

    @Autowired
    private IHouseMapApi houseMapApi;
    @HystrixCommand(fallbackMethod = "getMapHystrix",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public RestResponse<MapHouseDataResult> getMap(Float lng, Float lat, Integer zoom) {
        return houseMapApi.getMap(lng, lat, zoom);
    }
    private RestResponse<MapHouseDataResult> getMapHystrix(Float lng, Float lat, Integer zoom){
        System.out.println("服务超时");
        return RestResponse.FAIL;
    }
}