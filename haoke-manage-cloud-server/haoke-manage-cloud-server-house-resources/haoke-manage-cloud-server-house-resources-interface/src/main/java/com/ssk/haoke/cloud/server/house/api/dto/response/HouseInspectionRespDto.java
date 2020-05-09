package com.ssk.haoke.cloud.server.house.api.dto.response;

import com.ssk.haoke.cloud.server.house.api.dto.response.base.HouseResourcesBaseRespDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
@Data
public class HouseInspectionRespDto extends HouseResourcesBaseRespDto {
    /**
     *  主键
     */
    @ApiModelProperty(name = "id", value = "主键")
    private Long id;

    /**
     *  看房时间
     */
    @ApiModelProperty(name = "inspectionTime", value = "看房时间")
    private Date inspectionTime;

    /**
     *  请求时间
     */
    @ApiModelProperty(name = "reqTime", value = "请求时间")
    private Date reqTime;

    /**
     *  请求状态（0、待确认 1、待看房 2、已确认 3、已取消）
     */
    @ApiModelProperty(name = "reqStatus", value = "请求状态（0、待确认 1、待看房 2、已确认 3、已取消）")
    private String reqStatus;
    /**
     *  地址
     */
    @ApiModelProperty(name = "address", value = "地址")
    private String address;

}
