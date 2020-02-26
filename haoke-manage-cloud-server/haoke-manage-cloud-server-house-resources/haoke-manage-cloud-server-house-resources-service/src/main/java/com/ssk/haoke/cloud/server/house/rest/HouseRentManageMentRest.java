package com.ssk.haoke.cloud.server.house.rest;

import com.ssk.haoke.cloud.server.house.api.IHouseRentMgmtApi;
import com.ssk.haoke.cloud.server.house.api.dto.request.HouseRentMgmtReqDto;
import com.ssk.haoke.cloud.server.house.api.dto.response.HouseRentMgmtDetailRespDto;
import com.ssk.haoke.cloud.server.house.api.dto.response.HouseRentMgmtRespDto;
import com.ssk.haoke.cloud.server.house.api.query.IHouseRentMgmtQueryApi;
import com.ssk.haoke.cloud.server.house.eo.PageInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/v1/houserent")
public class HouseRentManageMentRest implements IHouseRentMgmtApi,IHouseRentMgmtQueryApi{
    @Resource
    private IHouseRentMgmtQueryApi houseRentMgmtQueryApi;
    @Resource
    private IHouseRentMgmtApi houseRentMgmtApi;
    @Override
    public RestResponse<Long> addRentManagement(HouseRentMgmtReqDto addReqDto) {
        return houseRentMgmtApi.addRentManagement(addReqDto);
    }

    @Override
    public RestResponse<Void> modifyRentManagement(HouseRentMgmtReqDto modifyReqDto) {
        return houseRentMgmtApi.modifyRentManagement(modifyReqDto);
    }

    @Override
    public RestResponse<Void> removeRentManagement(String ids) {
        return houseRentMgmtApi.removeRentManagement(ids);
    }

    @Override
    public RestResponse<HouseRentMgmtDetailRespDto> queryById(Long id) {
        return houseRentMgmtQueryApi.queryById(id);
    }

    @Override
    public RestResponse<PageInfo<HouseRentMgmtRespDto>> queryByPage(String filter, Integer pageNum, Integer pageSize) {
        return houseRentMgmtQueryApi.queryByPage(filter, pageNum, pageSize);
    }
}
