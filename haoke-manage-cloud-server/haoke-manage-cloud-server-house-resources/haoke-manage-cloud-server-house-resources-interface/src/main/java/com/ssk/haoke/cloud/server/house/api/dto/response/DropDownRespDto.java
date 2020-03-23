package com.ssk.haoke.cloud.server.house.api.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "下拉框标准")
public class DropDownRespDto {
    @ApiModelProperty(value = "value")
    private List<Long> value;
    @ApiModelProperty(value = "名字")
    private String label;
    @ApiModelProperty(value = "子节点")
    private List<DropDownRespDto> child;
}
