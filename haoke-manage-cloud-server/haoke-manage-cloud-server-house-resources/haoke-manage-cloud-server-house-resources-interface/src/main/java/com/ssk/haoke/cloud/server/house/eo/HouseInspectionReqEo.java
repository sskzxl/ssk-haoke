package com.ssk.haoke.cloud.server.house.eo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssk.haoke.cloud.server.pojo.BasePojo;
import lombok.Data;

import java.time.LocalDateTime;
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
    @JsonFormat(pattern = "yyyy-MM-dd hh:MM:ss")
    private LocalDateTime startTime;
    @JsonFormat(pattern = "yyyy-MM-dd hh:MM:ss")
    @TableField("end_time")
    private LocalDateTime endTime;
    @TableField("req_status")
    private Integer reqStatus;
    @TableField("req_msg")
    private String reqMsg;
    @TableField("req_time")
    private Date reqTime;


}