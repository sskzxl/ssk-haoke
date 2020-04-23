package com.ssk.haoke.cloud.server.house.apiimpl;

import com.ssk.haoke.cloud.server.house.api.IHouseResourcesApi;
import com.ssk.haoke.cloud.server.house.api.dto.request.HouseResourcesReqDto;
import com.ssk.haoke.cloud.server.house.rest.RestResponse;
import com.ssk.haoke.cloud.server.house.service.HouseResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("houseResourcesApi")
public class HouseResourcesApiImpl implements IHouseResourcesApi {

    @Autowired
    private HouseResourcesService houseResourcesService;

    @Override
    public RestResponse<Boolean> deleteHouseResource(Long id) {
        return new RestResponse<>(houseResourcesService.deleteHouseResources(id));
    }
    @Override
    public RestResponse<Long> saveHouseResources(HouseResourcesReqDto houseResourcesReqDto) {
        return new RestResponse<>(this.houseResourcesService.saveHouseResources(houseResourcesReqDto));
    }
    @Override
    public RestResponse<Boolean> updateHouseResources(HouseResourcesReqDto houseResourcesReqDto) {
        return new RestResponse<>(this.houseResourcesService.updateHouseResources(houseResourcesReqDto));
    }

    @Override
    public RestResponse sysnHouseData(List<Long> ids) {
        this.houseResourcesService.synsHouseData(ids);
        return RestResponse.VOID;
    }


}