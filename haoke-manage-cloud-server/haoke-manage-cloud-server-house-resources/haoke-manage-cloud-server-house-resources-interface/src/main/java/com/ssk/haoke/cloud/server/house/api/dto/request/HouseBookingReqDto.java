package com.ssk.haoke.cloud.server.house.api.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "HouseBookingReqDto", description = "看房预约请求dto对象")
public class HouseBookingReqDto {
    /**
     *  主键
     */
    @ApiModelProperty(name = "houseResourcesId", value = "房源id")
    private Long houseResourcesId;//房源信息的contract是房东的username
    @ApiModelProperty(name = "userId", value = "租客id")
    private Long userId;
    @ApiModelProperty(name = "address", value = "地址")
    private String address;
    @ApiModelProperty(name = "startTime", value = "预约时间")
    private Date startTime;
    @ApiModelProperty(name = "EndTime", value = "预约时间")
    private Date endTime;
    @ApiModelProperty(name = "reqTime", value = "请求时间")
    private Date reqTime;
    @ApiModelProperty(name = "phone", value = "租客电话")
    private String phone;
}
