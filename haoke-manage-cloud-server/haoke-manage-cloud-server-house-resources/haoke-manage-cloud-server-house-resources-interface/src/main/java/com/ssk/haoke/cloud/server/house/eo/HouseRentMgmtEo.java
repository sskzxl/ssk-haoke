package com.ssk.haoke.cloud.server.house.eo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ssk.haoke.cloud.server.pojo.BasePojo;
import lombok.Data;

/**
* 租房管理表Eo对象
*
* @author 代码生成器
*/
@TableName("tb_rent_management")
@Data
public class HouseRentMgmtEo extends BasePojo{
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    /**
     *租户姓名
     */
    @TableField("tenant_name")
    private String tenantName;
    /**
     *租户手机
     */
    @TableField("tenant_phone")
    private String tenantPhone;
    /**
     *租户id
     */
    @TableField("tenant_id")
    private Long tenantId;
    /**
     *房东id
     */
    @TableField("landlord_id")
    private Long landlordId;
    /**
     *房源id
     */
    @TableField("house_resources_id")
    private Long houseResourcesId;
    /**
     *房源姓名
     */
    @TableField("house_resources_name")
    private String houseResourcesName;

    /**
     *  租金
     */
    @TableField("rent")
    private String rent;

    /**
     *  0、已租 1、未租  2、取消
     */
    @TableField("status")
    private String status;
}