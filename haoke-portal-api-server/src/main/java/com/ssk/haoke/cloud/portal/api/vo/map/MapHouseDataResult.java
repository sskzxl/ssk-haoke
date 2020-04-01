package com.ssk.haoke.cloud.portal.api.vo.map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapHouseDataResult {
    private List<MapHouse> list;
    private Long total;

    public MapHouseDataResult(List<MapHouse> list) {
        this.list = list;
    }
}