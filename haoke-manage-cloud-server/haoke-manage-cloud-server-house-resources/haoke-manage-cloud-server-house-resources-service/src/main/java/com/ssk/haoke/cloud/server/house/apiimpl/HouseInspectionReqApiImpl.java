package com.ssk.haoke.cloud.server.house.apiimpl;

import com.ssk.haoke.cloud.server.house.api.IHouseInspectionReqApi;
import com.ssk.haoke.cloud.server.house.api.dto.request.HouseBookingReqDto;
import com.ssk.haoke.cloud.server.house.api.dto.request.HouseInspectionReqReqDto;
import com.ssk.haoke.cloud.server.house.rest.RestResponse;
import com.ssk.haoke.cloud.server.house.service.IHouseInspectionReqService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* 看房请求表服务实现类
*
* @author 代码生成器
*/
@Service("houseInspectionReqApi")
public class HouseInspectionReqApiImpl implements IHouseInspectionReqApi {

    @Resource
    private IHouseInspectionReqService houseInspectionReqService;

    @Override
    public RestResponse<Long> addHouseInspectionReq(HouseInspectionReqReqDto addReqDto) {
        return new RestResponse(houseInspectionReqService.addHouseInspectionReq(addReqDto));
    }

    @Override
    public RestResponse<Void> modifyHouseInspectionReq(HouseInspectionReqReqDto modifyReqDto) {
        houseInspectionReqService.modifyHouseInspectionReq(modifyReqDto);
        return RestResponse.VOID;
    }

    @Override
    public RestResponse<Void> removeHouseInspectionReq(String ids) {
        houseInspectionReqService.removeHouseInspectionReq(ids);
        return RestResponse.VOID;
    }

    @Override
    public RestResponse commitBooking(HouseBookingReqDto houseBookingReqDto) {
        return houseInspectionReqService.commitBooking(houseBookingReqDto);
    }
    //没用
    @Override
    public RestResponse updateStatus(Integer bookingStatus) {
        return new RestResponse(houseInspectionReqService.updateStatus(bookingStatus));
    }


}
