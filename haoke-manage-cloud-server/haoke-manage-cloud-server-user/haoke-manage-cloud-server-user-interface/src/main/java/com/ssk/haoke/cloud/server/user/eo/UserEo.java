package com.ssk.haoke.cloud.server.user.eo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ssk.haoke.cloud.server.pojo.BasePojo;
import lombok.Data;

/**
* Eo对象
*
* @author 代码生成器
*/
@TableName("tb_user")
@Data
public class UserEo extends BasePojo{
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    /**
     *  姓名
     */
    private String username;
    /**
     *  密码
     */
    private String password;
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

}