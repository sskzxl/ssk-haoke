package com.ssk.haoke.cloud.server.user.api.dto.request;

import com.ssk.haoke.cloud.server.pojo.BasePojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
* Eo对象
*
* @author 代码生成器
*/
@Data
@ApiModel(value = "ContractReqDto", description = "Eo对象")
public class ContractReqDto extends BasePojo {

    /**
    *  主键
    */
    @ApiModelProperty(name = "id", value = "主键")
    private Long id;

    /**
    *  合同状态 0、待签约 1、履约中 2、已结束 3、已逾期
    */
    @ApiModelProperty(name = "status", value = "合同状态 0、待签约 1、履约中 2、已结束 3、已逾期")
    private String status;

    /**
    *  房源id
    */
    @ApiModelProperty(name = "houseResourcesId", value = "房源id")
    private Integer houseResourcesId;

    /**
    *  物业地址（绑定楼盘）
    */
    @ApiModelProperty(name = "address", value = "物业地址（绑定楼盘）")
    private String address;

    /**
    *  业主姓名（房东）
    */
    @ApiModelProperty(name = "landlordName", value = "业主姓名（房东）")
    private String landlordName;

    /**
    *  客户姓名（租客）
    */
    @ApiModelProperty(name = "tenantName", value = "客户姓名（租客）")
    private String tenantName;

    /**
    *  签约日期
    */
    @ApiModelProperty(name = "startTime", value = "签约日期")
    private Date startTime;

    /**
    *  结束日期
    */
    @ApiModelProperty(name = "endTime", value = "结束日期")
    private Date endTime;

    /**
    *  房东id
    */
    @ApiModelProperty(name = "landlordId", value = "房东id")
    private Integer landlordId;

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