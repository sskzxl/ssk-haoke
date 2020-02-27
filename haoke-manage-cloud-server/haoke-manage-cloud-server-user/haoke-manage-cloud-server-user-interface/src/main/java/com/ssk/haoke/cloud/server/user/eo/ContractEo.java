package com.ssk.haoke.cloud.server.user.eo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ssk.haoke.cloud.server.pojo.BasePojo;
import lombok.Data;

import java.util.Date;

/**
* Eo对象
*
* @author 代码生成器
*/
@TableName("tb_contract")
@Data
public class ContractEo extends BasePojo{

    /**
     *  合同状态 0、待签约 1、履约中 2、已结束 3、已逾期
     */
    @TableField("status")
    private String status;

    /**
     *  房源id
     */
    @TableField("house_resources_id")
    private Integer houseResourcesId;

    /**
     *  物业地址（绑定楼盘）
     */
    @TableField("address")
    private String address;

    /**
     *  业主姓名（房东）
     */
    @TableField("landlord_name")
    private String landlordName;

    /**
     *  客户姓名（租客）
     */
    @TableField("tenant_name")
    private String tenantName;

    /**
     *  签约日期
     */
    @TableField("start_time")
    private Date startTime;

    /**
     *  结束日期
     */
    @TableField("end_time")
    private Date endTime;

    /**
     *  房东id
     */
    @TableField("landlord_id")
    private Integer landlordId;

    /**
     *  
     */
    @TableField("created")
    private Date created;

    /**
     *  
     */
    @TableField( "updated")
    private Date updated;


}