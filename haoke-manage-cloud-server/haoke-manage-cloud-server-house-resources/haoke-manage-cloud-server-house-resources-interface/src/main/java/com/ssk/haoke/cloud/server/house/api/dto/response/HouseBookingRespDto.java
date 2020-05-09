package com.ssk.haoke.cloud.server.house.api.dto.response;

import lombok.Data;

@Data
public class HouseBookingRespDto {
    /**
     * 房源信息
     */
    private HouseResourcesRespDto houseData;

    /**
     * 地址
     */
    private Integer reqStatus;
    /**
     * 电话
     */
    private String phone;
}
