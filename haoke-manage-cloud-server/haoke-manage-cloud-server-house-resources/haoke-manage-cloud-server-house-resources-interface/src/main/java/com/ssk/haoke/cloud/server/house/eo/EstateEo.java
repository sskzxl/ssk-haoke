package com.ssk.haoke.cloud.server.house.eo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ssk.haoke.cloud.server.pojo.BasePojo;
import lombok.Data;

/**
* 楼盘表Eo对象
*
* @author 代码生成器
*/
@TableName("tb_estate")
@Data
public class EstateEo extends BasePojo{

    @TableId
    private Long id;
    /**
     *  楼盘名称
     */
    @TableField("name")
    private String name;

    /**
     *  所在省
     */
    @TableField("province")
    private String province;

    /**
     *  所在市
     */
    @TableField("city")
    private String city;

    /**
     *  所在区
     */
    @TableField("area")
    private String area;

    /**
     *  具体地址
     */
    @TableField("address")
    private String address;

    /**
     *  建筑年代
     */
    @TableField("year")
    private String year;

    /**
     *  建筑类型
     */
    @TableField("type")
    private String type;

    /**
     *  物业费
     */
    @TableField("property_cost")
    private String propertyCost;

    /**
     *  物业公司
     */
    @TableField("property_company")
    private String propertyCompany;

    /**
     *  开发商
     */
    @TableField("developers")
    private String developers;



}