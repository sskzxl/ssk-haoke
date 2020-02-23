package com.ssk.haoke.cloud.server.api.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "RentMgmtDetailRespDto",description = "租房管理详细响应对象")
public class RentMgmtDetailRespDto {

    @ApiModelProperty(value = "houseResourcesRespDto",notes = "房源信息")
    private HouseResourcesRespDto houseResourcesRespDto;

    @ApiModelProperty(value = "landLord" , notes = "房东信息")
    private UserRespDto landLord;

    @ApiModelProperty(value = "tenant" , notes = "租客信息")
    private UserRespDto tenant;

    //合同信息
}
