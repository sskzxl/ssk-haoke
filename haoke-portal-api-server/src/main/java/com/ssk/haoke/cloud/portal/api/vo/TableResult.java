package com.ssk.haoke.cloud.portal.api.vo;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
public class TableResult<T> {
    private List<T> list;
    private Pagination pagination;
}