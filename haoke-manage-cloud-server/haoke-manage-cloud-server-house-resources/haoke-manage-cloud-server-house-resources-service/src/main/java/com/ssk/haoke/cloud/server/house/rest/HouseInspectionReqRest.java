package com.ssk.haoke.cloud.server.house.rest;

import com.ssk.haoke.cloud.server.house.api.IHouseInspectionReqApi;
import com.ssk.haoke.cloud.server.house.api.dto.request.HouseBookingReqDto;
import com.ssk.haoke.cloud.server.house.api.dto.request.HouseInspectionReqReqDto;
import com.ssk.haoke.cloud.server.house.api.dto.response.HouseBookingRespDto;
import com.ssk.haoke.cloud.server.house.api.dto.response.HouseInspectionRespDto;
import com.ssk.haoke.cloud.server.house.api.dto.response.HouseResourcesRespDto;
import com.ssk.haoke.cloud.server.house.api.query.IHouseInspectionReqQueryApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
    public RestResponse<List<Map<String,String>>> queryByPage(String filter, Integer pageNum, Integer pageSize) {
        return houseInspectionReqQueryApi.queryByPage(filter, pageNum, pageSize);
    }

    @Override
    public RestResponse<HouseResourcesRespDto> getHouseBooking(Long id) {
        return houseInspectionReqQueryApi.getHouseBooking(id);
    }

    @Override
    public RestResponse<String> getHostNote(Long id) {
        return houseInspectionReqQueryApi.getHostNote(id);
    }

    @Override
    public RestResponse<String> getTenantNote(Long id) {
        return houseInspectionReqQueryApi.getTenantNote(id);
    }




    @Override
    public RestResponse<Boolean> commitBooking(HouseBookingReqDto houseBookingReqDto) {
        return houseInspectionReqApi.commitBooking(houseBookingReqDto);
    }

    @Override
    public RestResponse updateStatus(Integer bookingStatus) {
        return null;
    }

    @Override
    public RestResponse<HouseBookingRespDto> getHouseBookingByReqId(Long id) {
        return houseInspectionReqQueryApi.getHouseBookingByReqId(id);
    }
}
