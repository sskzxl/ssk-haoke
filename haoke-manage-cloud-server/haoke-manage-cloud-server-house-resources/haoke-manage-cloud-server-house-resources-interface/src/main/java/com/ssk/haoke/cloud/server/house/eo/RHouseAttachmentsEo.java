package com.ssk.haoke.cloud.server.house.eo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ssk.haoke.cloud.server.pojo.BasePojo;
import lombok.Data;

/**
* Eo对象
*
* @author 代码生成器
*/
@Data
@TableName("tb_r_house_attachments")
public class RHouseAttachmentsEo extends BasePojo{
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    /**
     *  房源id
     */
    @TableField("house_resources_id")
    private Long houseResourcesId;

    /**
     *  房源配置id
     */
    @TableField("attachments_id")
    private Long attachmentsId;


}