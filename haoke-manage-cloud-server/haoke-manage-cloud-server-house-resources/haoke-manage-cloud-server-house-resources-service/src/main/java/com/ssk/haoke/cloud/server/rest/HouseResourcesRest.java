package com.ssk.haoke.cloud.server.rest;

import com.ssk.haoke.cloud.server.api.IHouseResourcesApi;
import com.ssk.haoke.cloud.server.api.dto.request.HouseResourcesReqDto;
import com.ssk.haoke.cloud.server.api.dto.response.HouseResourcesRespDto;
import com.ssk.haoke.cloud.server.api.query.IHouseResourcesQueryApi;
import com.ssk.haoke.cloud.server.vo.PageInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/v1/house/resources")
public class HouseResourcesRest implements IHouseResourcesApi,IHouseResourcesQueryApi {
    @Resource
    private IHouseResourcesApi houseResourcesApi;
    @Resource
    private IHouseResourcesQueryApi houseResourcesQueryApi;

    @Override
    public RestResponse<Boolean> deleteHouseResource(Long id) {
        return houseResourcesApi.deleteHouseResource(id);
    }

    @Override
    public RestResponse<Integer> saveHouseResources(HouseResourcesReqDto houseResourcesReqDto) {
        return houseResourcesApi.saveHouseResources(houseResourcesReqDto);
    }

    @Override
    public RestResponse<PageInfo<HouseResourcesRespDto>> queryHouseResourcesList(String filter,Integer page, Integer pageSize) {
        return houseResourcesQueryApi.queryHouseResourcesList(filter,page,pageSize);
    }

    @Override
    public RestResponse<HouseResourcesRespDto> queryHouseResourcesById(Long id) {
        return houseResourcesQueryApi.queryHouseResourcesById(id);
    }

    @Override
    public RestResponse<Boolean> updateHouseResources(HouseResourcesReqDto houseResources) {
        return updateHouseResources(houseResources);
    }
}
