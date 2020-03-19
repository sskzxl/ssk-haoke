package com.ssk.haoke.cloud.portal.api.controller;

import com.ssk.haoke.cloud.server.house.api.IHouseInspectionReqApi;
import com.ssk.haoke.cloud.server.house.api.dto.request.HouseBookingReqDto;
import com.ssk.haoke.cloud.server.house.api.dto.request.HouseInspectionReqReqDto;
import com.ssk.haoke.cloud.server.house.api.dto.response.HouseBookingRespDto;
import com.ssk.haoke.cloud.server.house.api.query.IHouseInspectionReqQueryApi;
import com.ssk.haoke.cloud.server.house.rest.RestResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("house/book")
public class HouseBookController {
    @Resource
    private IHouseInspectionReqQueryApi houseInspectionReqQueryApi;
    @Resource
    private IHouseInspectionReqApi houseInspectionReqApi;

    @GetMapping("/{id}")
    @ApiOperation(value = "根据房源id，获取房源信息和地址",notes = "租客联系房东预览页面")
    public RestResponse<HouseBookingRespDto> getBookingPage(@PathVariable("id") Long id){
        return houseInspectionReqQueryApi.getHouseBooking(id);
    }
    @PostMapping("/commit")
    public RestResponse commitBooking(@RequestBody HouseBookingReqDto houseBookingReqDto){
        return houseInspectionReqApi.commitBooking(houseBookingReqDto);
    }
//    @GetMapping("/")
}
