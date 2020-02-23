package com.ssk.haoke.cloud.server.apiimpl.query;

import com.ssk.haoke.cloud.server.api.dto.response.HouseResourcesRespDto;
import com.ssk.haoke.cloud.server.api.query.IHouseResourcesQueryApi;
import com.ssk.haoke.cloud.server.rest.RestResponse;
import com.ssk.haoke.cloud.server.service.HouseResourcesService;
import com.ssk.haoke.cloud.server.vo.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service("houseResourcesQueryApi")
public class HouseResourcesQueryApiImpl implements IHouseResourcesQueryApi {
    @Resource
    private HouseResourcesService houseResourcesService;
    @Override
    public RestResponse<PageInfo<HouseResourcesRespDto>> queryHouseResourcesList(String filter,Integer page, Integer pageSize) {
        return new RestResponse<>(this.houseResourcesService.queryHouseResourcesList(filter,page,pageSize));
    }

    @Override
    public RestResponse<HouseResourcesRespDto> queryHouseResourcesById(Long id) {
        return new RestResponse<>(this.houseResourcesService.queryHouseResourcesById(id));
    }

}
