package com.ssk.haoke.cloud.server.house.api.dto.response;

import lombok.Data;

@Data
public class HouseBookingRespDto {
    /**
     * 房源信息
     */
    private HouseResourcesRespDto houseResourcesRespDto;

    /**
     * 地址
     */
    private String address;
}
