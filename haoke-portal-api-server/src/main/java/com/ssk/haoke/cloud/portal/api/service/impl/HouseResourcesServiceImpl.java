package com.ssk.haoke.cloud.portal.api.service.impl;

import com.ssk.haoke.cloud.server.house.api.IHouseRentMgmtApi;
import com.ssk.haoke.cloud.server.house.api.IHouseResourcesApi;
import com.ssk.haoke.cloud.server.house.api.dto.request.HouseResourcesReqDto;
import com.ssk.haoke.cloud.server.house.api.dto.response.DropDownRespDto;
import com.ssk.haoke.cloud.server.house.api.dto.response.HouseResourcesListRespDto;
import com.ssk.haoke.cloud.server.house.api.dto.response.HouseResourcesRespDto;
import com.ssk.haoke.cloud.server.house.api.query.IHouseRentMgmtQueryApi;
import com.ssk.haoke.cloud.server.house.api.query.IHouseResourcesQueryApi;
import com.ssk.haoke.cloud.server.house.eo.PageInfo;
import com.ssk.haoke.cloud.server.house.rest.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("houseResourcesService")
public class HouseResourcesServiceImpl {
    @Autowired(required = false)
    private IHouseResourcesApi houseResourcesApi;

    @Resource
    private IHouseRentMgmtApi houseRentMgmtApi;
    @Resource
    private IHouseRentMgmtQueryApi houseRentMgmtQueryApi;
    @Resource
    private IHouseResourcesQueryApi houseResourcesQueryApi;
    //删除房源
    public RestResponse<Boolean> delete(Long id){
        return this.houseResourcesApi.deleteHouseResource(id);
    }
    //增加房源
    public RestResponse<Long> save(HouseResourcesReqDto houseResourcesReqDto) {
        return  this.houseResourcesApi.saveHouseResources(houseResourcesReqDto);
    }
    //查询房源列表
    public RestResponse<PageInfo<HouseResourcesListRespDto>> queryList(Integer pageNum, Integer pageSize, String filter) {
        RestResponse<PageInfo<HouseResourcesListRespDto>> response = this.houseResourcesQueryApi.queryHouseResourcesList(filter, pageNum, pageSize);
        return response;
    }
    /**
     * @param id
     * @return
     */
    public RestResponse<HouseResourcesRespDto> queryById(Long id){
        return this.houseResourcesQueryApi.queryHouseResourcesById(id);
    }
    /*
        更新房源
     */
    public RestResponse<Boolean> update(HouseResourcesReqDto houseResourcesReqDto) {
        return this.houseResourcesApi.updateHouseResources(houseResourcesReqDto);
    }

    /**
     * 根据楼盘城市查询房源
     */
    public RestResponse<PageInfo<HouseResourcesRespDto>> getPageByCity(String cityName,Integer pageNum,Integer pageSize) {
        return this.houseResourcesQueryApi.getPageByCity(cityName, pageNum, pageSize);
    }
    public RestResponse<List<DropDownRespDto>> getAllCity(){
        return this.houseResourcesQueryApi.getAllCity();
    }
    public RestResponse sysnHouseData(List<Long> ids){
        return this.houseResourcesApi.sysnHouseData(ids);
    }
}