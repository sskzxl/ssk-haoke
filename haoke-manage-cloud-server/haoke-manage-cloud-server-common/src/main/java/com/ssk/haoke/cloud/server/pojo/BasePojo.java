package com.ssk.haoke.cloud.server.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class BasePojo implements java.io.Serializable{
    /**
     *
     */
    @ApiModelProperty(name = "created", value = "")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;

    /**
     *
     */
    @ApiModelProperty(name = "updated", value = "")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updated;
}
