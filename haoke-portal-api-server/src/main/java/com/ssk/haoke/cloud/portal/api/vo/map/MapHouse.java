package com.ssk.haoke.cloud.portal.api.vo.map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapHouse {
    private Float x;
    private Float y;
    private String title;
    private Long houseResourcesId;
}