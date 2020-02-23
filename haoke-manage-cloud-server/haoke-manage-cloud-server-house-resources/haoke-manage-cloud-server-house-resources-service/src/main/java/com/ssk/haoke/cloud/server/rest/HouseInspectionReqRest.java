package com.ssk.haoke.cloud.server.rest;

import com.ssk.haoke.cloud.server.api.IHouseInspectionReqApi;
import com.ssk.haoke.cloud.server.api.dto.request.HouseInspectionReqReqDto;
import com.ssk.haoke.cloud.server.api.dto.response.HouseInspectionRespDto;
import com.ssk.haoke.cloud.server.api.query.IHouseInspectionReqQueryApi;
import com.ssk.haoke.cloud.server.vo.PageInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
@RestController
@RequestMapping("/v1/houseinspectionreq")
public class HouseInspectionReqRest implements IHouseInspectionReqApi,IHouseInspectionReqQueryApi {
    @Resource
    private IHouseInspectionReqQueryApi houseInspectionReqQueryApi;
    @Resource
    private IHouseInspectionReqApi houseInspectionReqApi;
    @Override
    public RestResponse<Long> addHouseInspectionReq(HouseInspectionReqReqDto addReqDto) {
        return houseInspectionReqApi.addHouseInspectionReq(addReqDto);
    }

    @Override
    public RestResponse<Void> modifyHouseInspectionReq(HouseInspectionReqReqDto modifyReqDto) {
        return houseInspectionReqApi.modifyHouseInspectionReq(modifyReqDto);
    }

    @Override
    public RestResponse<Void> removeHouseInspectionReq(String ids) {
        return houseInspectionReqApi.removeHouseInspectionReq(ids);
    }

    @Override
    public RestResponse<HouseInspectionRespDto> queryById(Long id) {
        return houseInspectionReqQueryApi.queryById(id);
    }

    @Override
    public RestResponse<PageInfo<HouseInspectionRespDto>> queryByPage(String filter, Integer pageNum, Integer pageSize) {
        return houseInspectionReqQueryApi.queryByPage(filter, pageNum, pageSize);
    }
}
