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
    /**
     *  用户id（tb_user）
     */
    @TableId
    private Long userId;

    /**
     *  房源id（tb_house_resources）
     */
    @TableField("house_resouces_id")
    private Long houseResoucesId;

    /**
     *  看房时间
     */
    @TableField("inspection_time")
    private Date inspectionTime;

    /**
     *  请求时间x
     */
    @TableField("req_time")
    private Date reqTime;

    /**
     *  请求状态（0、待确认 1、待看房 2、已确认 3、已取消）
     */
    @TableField("req_status")
    private String reqStatus;

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