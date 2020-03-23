package com.ssk.haoke.cloud.server.house.apiimpl.query;

import com.ssk.haoke.cloud.server.house.api.dto.response.DropDownRespDto;
import com.ssk.haoke.cloud.server.house.api.dto.response.HouseResourcesRespDto;
import com.ssk.haoke.cloud.server.house.api.query.IHouseResourcesQueryApi;
import com.ssk.haoke.cloud.server.house.eo.PageInfo;
import com.ssk.haoke.cloud.server.house.rest.RestResponse;
import com.ssk.haoke.cloud.server.house.service.HouseResourcesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("houseResourcesQueryApi")
public class HouseResourcesQueryApiImpl implements IHouseResourcesQueryApi {
    @Resource
    private HouseResourcesService houseResourcesService;
    @Override
    public RestResponse<PageInfo<HouseResourcesRespDto>> queryHouseResourcesList(String filter,Integer pageNum, Integer pageSize) {
        return new RestResponse<>(this.houseResourcesService.queryHouseResourcesList(filter,pageNum,pageSize));
    }

    @Override
    public RestResponse<HouseResourcesRespDto> queryHouseResourcesById(Long id) {
        return new RestResponse<>(this.houseResourcesService.queryHouseResourcesById(id));
    }

    @Override
    public RestResponse<PageInfo<HouseResourcesRespDto>> getPageByCity(String cityName, Integer pageNum, Integer pageSize) {
        return new RestResponse<>(this.houseResourcesService.getPageByCity(cityName, pageNum, pageSize));
    }
    public RestResponse<List<DropDownRespDto>> getAllCity(){
        return new RestResponse<>(this.houseResourcesService.getAllCity());
    }

}
