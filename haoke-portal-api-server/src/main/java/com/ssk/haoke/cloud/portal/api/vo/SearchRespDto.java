package com.ssk.haoke.cloud.portal.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "搜索响应dto")
public class SearchRespDto {
    @ApiModelProperty(value = "totalPage",name = "总页数")
    private Integer totalPage;
    @ApiModelProperty(value = "list",name = "总页数")
    private List<HouseData> list;
    @ApiModelProperty(value = "hotWord",name = "房源丰富热词")
    private Set<String> hotWord;
    @ApiModelProperty(value = "recommendWord",name = "搜素丰富热词")
    private Set<String> recommendWord;
}