package com.ssk.haoke.cloud.server.common;

import com.google.common.collect.Maps;

import java.util.Map;

public class AttachMap {
    public static Map<Long,String> attachMap = Maps.newHashMap();
    static {
        attachMap.put(1L,"bed.jpg");
        attachMap.put(2L,"washer.jpg");
        attachMap.put(3L,"airCondition.jpg");
        attachMap.put(4L,"wardrobe.jpg");
        attachMap.put(5L,"TV.jpg");
        attachMap.put(6L,"fridge.jpg");
        attachMap.put(7L,"waterHeater.jpg");
        attachMap.put(8L,"warm.jpg");
        attachMap.put(9L,"broadband.jpg");
        attachMap.put(10L,"fire.jpg");
    }
}
