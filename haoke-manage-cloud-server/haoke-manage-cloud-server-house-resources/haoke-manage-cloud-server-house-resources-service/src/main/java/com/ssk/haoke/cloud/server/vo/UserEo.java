package com.ssk.haoke.cloud.server.vo;

import lombok.Data;

import java.util.Date;

/**
* Eo对象
*
* @author 代码生成器
*/
@Data
public class UserEo{

    /**
     *  姓名
     */
    private String name;

    /**
     *  手机号
     */
    private String phone;

    /**
     *  性别（0、男 1、女）
     */
    private Integer sex;

    /**
     *  身份证号
     */
    private String identityCard;

    /**
     *  职业信息
     */
    private String professionInfo;

    /**
     *  学历（0、高中以下 1、大专 2、本科 3、硕士 4、博士以上）
     */
    private Integer educationBg;

    /**
     *  是否认证（0、是 1、否）
     */
    private Integer isAuthenticated;

    /**
     *  是否房东（0、是 1、否）
     */
    private Integer isHost;

    /**
     *  
     */
    private Date created;

    /**
     *  
     */
    private Date updated;

}