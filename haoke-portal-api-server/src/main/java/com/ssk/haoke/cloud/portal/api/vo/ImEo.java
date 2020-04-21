package com.ssk.haoke.cloud.portal.api.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ssk.haoke.cloud.server.pojo.BasePojo;
import lombok.Data;

/**
* Eo对象
*
* @author 代码生成器
*/
@TableName("tb_im")
@Data
public class ImEo extends BasePojo{

    private Long id;
    /**
     *  发出信息用户id
     */
    private Long fromId;

    /**
     *  发出信息用户名
     */
    private String fromUsername;

    /**
     *  接收信息用户id
     */
    private Long toId;

    /**
     *  接收信息用户名
     */
    private String toUsername;

    /**
     *  
     */


}