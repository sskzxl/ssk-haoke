package com.ssk.haoke.cloud.server.house.api.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
* 租房管理表Eo对象
*
* @author 代码生成器
*/
@Data
@ApiModel(value = "RentManagementReqDto", description = "租房管理表Eo对象")
public class HouseRentMgmtReqDto{

    /**
    *  主键
    */
    @ApiModelProperty(name = "id", value = "主键")
    private Long id;

    /**
    *  租户姓名
    */
    @ApiModelProperty(name = "rentingName", value = "租户姓名")
    private String rentingName;
    /**
     *  房东姓名
     */
    @ApiModelProperty(name = "LandlordName", value = "房东姓名")
    private String LandlordName;
    /**
    *  房源名称
    */
    @ApiModelProperty(name = "houseResources", value = "房源名称")
    private Long houseResources;

    /**
    *  合同开始时间
    */
    @ApiModelProperty(name = "startTime", value = "合同开始时间")
    private Date startTime;

    /**
    *  合同结束时间
    */
    @ApiModelProperty(name = "endTime", value = "合同结束时间")
    private Date endTime;

    /**
    *  租金
    */
    @ApiModelProperty(name = "rent", value = "租金")
    private String rent;

    /**
    *  0、待确认 1、已确认 2、待付款 3、已逾期
    */
    @ApiModelProperty(name = "status", value = "0、待确认 1、已确认 2、待付款 3、已逾期")
    private String status;

    /**
    *  
    */
    @ApiModelProperty(name = "created", value = "")
    private Date created;

    /**
    *  
    */
    @ApiModelProperty(name = "updated", value = "")
    private Date updated;



}