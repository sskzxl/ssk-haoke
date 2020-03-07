package com.ssk.haoke.cloud.manage.api.service.impl;

import com.ssk.haoke.cloud.server.house.api.IHouseRentMgmtApi;
import com.ssk.haoke.cloud.server.house.api.dto.request.HouseRentMgmtReqDto;
import com.ssk.haoke.cloud.server.house.api.dto.response.HouseRentMgmtDetailRespDto;
import com.ssk.haoke.cloud.server.house.api.dto.response.HouseRentMgmtRespDto;
import com.ssk.haoke.cloud.server.house.api.query.IHouseRentMgmtQueryApi;
import com.ssk.haoke.cloud.server.house.eo.PageInfo;
import com.ssk.haoke.cloud.server.house.rest.RestResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("houseRentMgmtService")
public class HouseRentMgmtServiceImpl {
    @Resource
    private IHouseRentMgmtApi houseRentMgmtApi;
    @Resource
    private IHouseRentMgmtQueryApi houseRentMgmtQueryApi;

    //删除租房管理
    public RestResponse<Void> delete(String ids){
        return this.houseRentMgmtApi.removeRentManagement(ids);
    }
    //增加租房管理
    public RestResponse<Long> save(HouseRentMgmtReqDto houseRentMgmtReqDto) {
        RestResponse<Long> result = this.houseRentMgmtApi.addRentManagement(houseRentMgmtReqDto);
        return result;
    }
    //查询租房管理列表
    public RestResponse<PageInfo<HouseRentMgmtRespDto>> queryList(Integer pageNum, Integer pageSize, String filter) {
        RestResponse<PageInfo<HouseRentMgmtRespDto>> response = this.houseRentMgmtQueryApi.queryByPage(filter, pageNum, pageSize);

        return response;
    }
    /**
     * @param id
     * @return
     */
    public RestResponse<HouseRentMgmtDetailRespDto> queryById(Long id){
        return this.houseRentMgmtQueryApi.queryById(id);
    }
    /*
        更新租房管理
     */
    public RestResponse<Void> update(HouseRentMgmtReqDto houseRentMgmtReqDto) {
        return this.houseRentMgmtApi.modifyRentManagement(houseRentMgmtReqDto);
    }



}