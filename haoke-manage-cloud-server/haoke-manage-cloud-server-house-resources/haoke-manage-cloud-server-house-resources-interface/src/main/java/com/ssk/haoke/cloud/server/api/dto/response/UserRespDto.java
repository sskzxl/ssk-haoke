package com.ssk.haoke.cloud.server.api.dto.response;

import com.ssk.haoke.cloud.server.pojo.BasePojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
*
* @author 代码生成器
*/
@Data
@ApiModel(value = "UserRespDto", description = "用户响应dto对象（租户或房东）")
public class UserRespDto extends BasePojo{

    /**
    *  主键
    */
    @ApiModelProperty(name = "id", value = "主键")
    private Long id;

    /**
    *  姓名
    */
    @ApiModelProperty(name = "name", value = "姓名")
    private String name;

    /**
    *  手机号
    */
    @ApiModelProperty(name = "phone", value = "手机号")
    private String phone;

    /**
    *  性别（0、男 1、女）
    */
    @ApiModelProperty(name = "sex", value = "性别（0、男 1、女）")
    private Integer sex;

    /**
    *  身份证号
    */
    @ApiModelProperty(name = "identityCard", value = "身份证号")
    private String identityCard;

    /**
    *  职业信息
    */
    @ApiModelProperty(name = "professionInfo", value = "职业信息")
    private String professionInfo;

    /**
    *  学历（0、高中以下 1、大专 2、本科 3、硕士 4、博士以上）
    */
    @ApiModelProperty(name = "educationBg", value = "学历（0、高中以下 1、大专 2、本科 3、硕士 4、博士以上）")
    private Integer educationBg;

    /**
    *  是否认证（0、是 1、否）
    */
    @ApiModelProperty(name = "isAuthenticated", value = "是否认证（0、是 1、否）")
    private Integer isAuthenticated;

    /**
    *  是否房东（0、是 1、否）
    */
    @ApiModelProperty(name = "isHost", value = "是否房东（0、是 1、否）")
    private Integer isHost;


}