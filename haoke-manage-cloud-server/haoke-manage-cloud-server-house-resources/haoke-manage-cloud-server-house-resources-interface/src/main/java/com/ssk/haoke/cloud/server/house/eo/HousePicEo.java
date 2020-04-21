package com.ssk.haoke.cloud.server.house.eo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ssk.haoke.cloud.server.pojo.BasePojo;
import lombok.Data;

/**
* Eo对象
*
* @author 代码生成器
*/
@TableName("tb_house_pic")
@Data
public class HousePicEo extends BasePojo{

    /**
     *  图片名称
     */
    @TableField("name")
    private String name;

    /**
     *  图片路径
     */
    @TableField("path")
    private String path;

    /**
     *  文件名
     */
    @TableField("file_name")
    private String fileName;

    /**
     *  房源id
     */
    @TableField("house_resources_id")
    private Long houseResourcesId;

}