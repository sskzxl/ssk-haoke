package com.ssk.haoke.cloud.server.house.eo;

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
@TableName("tb_attachments")
public class AttachmentsEo extends BasePojo{

    @TableId
    private Long id;
    /**
     *  配置名
     */
    @TableField("name")
    private String name;

    /**
     *  图标
     */
    @TableField("icon_name")
    private String iconName;


    /**
     *  
     */
    @TableField("url")
    private String url;


}