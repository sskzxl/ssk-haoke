package com.ssk.haoke.cloud.server.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pagination {
    private Integer current;
    private Integer pageSize;
    private Integer total;
}