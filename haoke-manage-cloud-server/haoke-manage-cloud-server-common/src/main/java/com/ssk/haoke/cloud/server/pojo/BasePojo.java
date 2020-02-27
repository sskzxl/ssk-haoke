package com.ssk.haoke.cloud.server.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public abstract class BasePojo implements java.io.Serializable{
    /**
     *
     */
    @ApiModelProperty(name = "created", value = "")
    private Date created;

    /**
     *
     */
    @ApiModelProperty(name = "updated", value = "")
    private Date updated;
}
