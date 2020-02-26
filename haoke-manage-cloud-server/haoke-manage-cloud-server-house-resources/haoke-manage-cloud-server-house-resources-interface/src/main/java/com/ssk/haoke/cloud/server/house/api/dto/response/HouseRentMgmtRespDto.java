package com.ssk.haoke.cloud.server.house.api.dto.response;

import com.ssk.haoke.cloud.server.house.api.dto.response.base.HouseResourcesBaseRespDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "HouseRentMgmtRespDto", description = "租房管理响应dto对象")
public class HouseRentMgmtRespDto extends HouseResourcesBaseRespDto{
    /**
     * 主键
     */
    @ApiModelProperty(name = "id", value = "主键")
    private Long id;

    /**
     * 合同开始时间
     */
    @ApiModelProperty(name = "startTime", value = "合同开始时间")
    private Date startTime;

    /**
     * 合同结束时间
     */
    @ApiModelProperty(name = "endTime", value = "合同结束时间")
    private Date endTime;

    /**
     * 租金
     */
    @ApiModelProperty(name = "rent", value = "租金")
    private String rent;

    /**
     * 0、待确认 1、已确认 2、待付款 3、已逾期
     */
    @ApiModelProperty(name = "status", value = "0、待确认 1、已确认 2、待付款 3、已逾期")
    private String status;


}
