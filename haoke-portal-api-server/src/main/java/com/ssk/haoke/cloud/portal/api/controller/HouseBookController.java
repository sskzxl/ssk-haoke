package com.ssk.haoke.cloud.portal.api.controller;

import com.ssk.haoke.cloud.server.house.api.IHouseInspectionReqApi;
import com.ssk.haoke.cloud.server.house.api.dto.request.HouseBookingReqDto;
import com.ssk.haoke.cloud.server.house.api.dto.request.HouseInspectionReqReqDto;
import com.ssk.haoke.cloud.server.house.api.dto.response.HouseBookingRespDto;
import com.ssk.haoke.cloud.server.house.api.dto.response.HouseResourcesRespDto;
import com.ssk.haoke.cloud.server.house.api.query.IHouseInspectionReqQueryApi;
import com.ssk.haoke.cloud.server.house.rest.RestResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("house/book")
public class HouseBookController {
    @Resource
    private IHouseInspectionReqQueryApi houseInspectionReqQueryApi;
    @Resource
    private IHouseInspectionReqApi houseInspectionReqApi;

    @GetMapping("/{id}")
    @ApiOperation(value = "根据房源id，获取房源信息和地址",notes = "租客联系房东预览页面")
    public RestResponse<HouseResourcesRespDto> getBookingPage(@PathVariable("id") Long id){
        return houseInspectionReqQueryApi.getHouseBooking(id);
    }
    @PostMapping("/commit")
    public RestResponse commitBooking(@RequestBody HouseBookingReqDto houseBookingReqDto){
        return houseInspectionReqApi.commitBooking(houseBookingReqDto);
    }
    @PutMapping("")
    @ApiOperation(value = "修改看房请求表", notes = "修改看房请求表")
    RestResponse<Void> modifyHouseInspectionReq(@RequestBody HouseInspectionReqReqDto modifyReqDto){
        return houseInspectionReqApi.modifyHouseInspectionReq(modifyReqDto);
    }
    @GetMapping("/page")
    @ApiOperation(value = "看房请求表分页数据", notes = "根据filter查询条件查询看房请求表数据，filter=HouseInspectionReqReqDto")
    RestResponse <List<Map<String,String>>> queryByPage(@RequestParam("filter") String filter,
                                                       @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                                       @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize){
        return houseInspectionReqQueryApi.queryByPage(filter, pageNum, pageSize);
    }
    @GetMapping("/note")
    @ApiOperation(value = "根据用户id查询看房请求通知",notes = "根据用户id查询看房请求通知")
    RestResponse<Map<Long, String>> getNote(@RequestParam("id")Long id){
        return null;
    }
    @GetMapping("/req/{id}")
    @ApiOperation(value = "根据看房请求表id查询看房请求通知",notes = "租客查看请求详情页面")
    public RestResponse<HouseBookingRespDto> getBookingByReqId(@PathVariable("id") Long id){
        return houseInspectionReqQueryApi.getHouseBookingByReqId(id);
    }
}
