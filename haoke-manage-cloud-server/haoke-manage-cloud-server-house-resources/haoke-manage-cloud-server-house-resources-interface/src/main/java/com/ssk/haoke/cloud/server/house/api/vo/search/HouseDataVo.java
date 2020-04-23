package com.ssk.haoke.cloud.server.house.api.vo.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseDataVo {
    private String id;
    private String title;
    private String rent;
    private String floor;
    private String image;
    private String orientation;
    private String houseType;
    private String rentMethod;
    private String time;
    private String address;
}