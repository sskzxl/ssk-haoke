package com.ssk.haoke.cloud.server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("tb_ad")
public class Ad extends BasePojo{

    private static final long serialVersionUID = 1995389979806858251L;
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(name = "id" , value = "id主键")
    private Long id;
    //广告类型
    @ApiModelProperty(name = "type" , value = "广告类型")
    private Integer type;
    //描述
    @ApiModelProperty(name = "title" , value = "描述")
    private String title;
    //'图片URL地址
    @ApiModelProperty(name = "url" , value = "图片URL地址")
    private String url;

}