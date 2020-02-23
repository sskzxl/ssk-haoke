package com.ssk.haoke.cloud.server.rest;

import com.ssk.haoke.cloud.server.api.IHouseRentMgmtApi;
import com.ssk.haoke.cloud.server.api.dto.request.HouseRentMgmtReqDto;
import com.ssk.haoke.cloud.server.api.dto.response.HouseRentMgmtRespDto;
import com.ssk.haoke.cloud.server.api.query.IHouseRentMgmtQueryApi;
import com.ssk.haoke.cloud.server.vo.PageInfo;
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
    public RestResponse<HouseRentMgmtRespDto> queryById(Long id) {
        return houseRentMgmtQueryApi.queryById(id);
    }

    @Override
    public RestResponse<PageInfo<HouseRentMgmtRespDto>> queryByPage(String filter, Integer pageNum, Integer pageSize) {
        return houseRentMgmtQueryApi.queryByPage(filter, pageNum, pageSize);
    }
}
