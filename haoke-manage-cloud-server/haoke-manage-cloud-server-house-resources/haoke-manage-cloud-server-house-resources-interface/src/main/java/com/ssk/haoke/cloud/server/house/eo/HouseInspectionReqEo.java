package com.ssk.haoke.cloud.server.house.eo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ssk.haoke.cloud.server.pojo.BasePojo;
import lombok.Data;

import java.util.Date;

/**
* 看房请求表Eo对象
*
* @author 代码生成器
*/
@TableName("tb_house_inspection_req")
@Data
public class HouseInspectionReqEo extends BasePojo{
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    @TableField("tenant_id")
    private Long tenantId;//租客id
    @TableField("host_id")
    private Long hostId;//房东id
    @TableField("house_resources_id")
    private Long houseResourcesId;
    @TableField("start_time")
    private Date startTime;
    @TableField("end_time")
    private Date endTime;
    @TableField("req_status")
    private Integer reqStatus;
    @TableField("address")
    private String address;
    @TableField("req_time")
    private Date reqTime;


}