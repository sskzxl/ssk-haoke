package com.ssk.haoke.cloud.server.house.apiimpl.query;

import com.ssk.haoke.cloud.server.house.api.IHouseMapApi;
import com.ssk.haoke.cloud.server.house.api.vo.map.MapHouseDataResult;
import com.ssk.haoke.cloud.server.house.rest.RestResponse;
import com.ssk.haoke.cloud.server.house.service.impl.HouseMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("houseMapApi")
public class HouseMapApiImpl implements IHouseMapApi {
    @Autowired
    private HouseMapService houseMapService;
    @Override
    public RestResponse<MapHouseDataResult> getMap(Float lng, Float lat, Integer zoom) {
        return new RestResponse<>(houseMapService.queryHouseData(lng, lat, zoom));
    }
}
