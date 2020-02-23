package com.ssk.haoke.cloud.server.apiimpl;

import com.ssk.haoke.cloud.server.api.IHouseResourcesApi;
import com.ssk.haoke.cloud.server.api.dto.request.HouseResourcesReqDto;
import com.ssk.haoke.cloud.server.rest.RestResponse;
import com.ssk.haoke.cloud.server.service.HouseResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("houseResourcesApi")
public class HouseResourcesApiImpl implements IHouseResourcesApi {

    @Autowired
    private HouseResourcesService houseResourcesService;

    @Override
    public RestResponse<Boolean> deleteHouseResource(Long id) {
        return new RestResponse<>(houseResourcesService.deleteHouseResources(id));
    }
    @Override
    public RestResponse<Integer> saveHouseResources(HouseResourcesReqDto houseResourcesReqDto) {
        return new RestResponse<>(this.houseResourcesService.saveHouseResources(houseResourcesReqDto));
    }
    @Override
    public RestResponse<Boolean> updateHouseResources(HouseResourcesReqDto houseResourcesReqDto) {
        return new RestResponse<>(this.houseResourcesService.updateHouseResources(houseResourcesReqDto));
    }


}