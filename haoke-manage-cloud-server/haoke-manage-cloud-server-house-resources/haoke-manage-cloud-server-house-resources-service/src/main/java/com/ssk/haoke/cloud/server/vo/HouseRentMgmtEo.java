package com.ssk.haoke.cloud.server.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ssk.haoke.cloud.server.pojo.BasePojo;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
* 租房管理表Eo对象
*
* @author 代码生成器
*/
@TableName("tb_rent_management")
public class HouseRentMgmtEo extends BasePojo{
    @TableId(value = "id",type = IdType.AUTO)
    @ApiModelProperty(name = "id" , value = "主键")
    private Long id;
    /**
     *
     */
    @TableField("user_id")
    private Long userId;

    /**
     *
     */
    @TableField("house_resources_id")
    private Long houseResourcesId;

    /**
     *  合同开始时间
     */
    @TableField("start_time")
    private Date startTime;

    /**
     *  合同结束时间
     */
    @TableField("end_time")
    private Date endTime;

    /**
     *  租金
     */
    @TableField("rent")
    private String rent;

    /**
     *  0、待确认 1、已确认 2、待付款 3、已逾期
     */
    @TableField("status")
    private String status;

    /**
     *
     */
    @TableField("created")
    private Date created;

    /**
     *
     */
    @TableField("updated")
    private Date updated;


}