package com.ssk.haoke.cloud.server.house.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ssk.haoke.cloud.server.house.api.vo.map.MapHouse;
import com.ssk.haoke.cloud.server.house.api.vo.map.MapHouseDataResult;
import com.ssk.haoke.cloud.server.house.api.vo.map.MongoHouse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HouseMapService {
    public static final Map<Integer, Double> BAIDU_ZOOM = new HashMap<>();

    static {
        //缩放级别和距离对应数据
        BAIDU_ZOOM.put(19, 20d / 1000); //单位为km
        BAIDU_ZOOM.put(18, 50d / 1000);
        BAIDU_ZOOM.put(17, 100d / 1000);
        BAIDU_ZOOM.put(16, 200d / 1000);
        BAIDU_ZOOM.put(15, 500d / 1000);
        BAIDU_ZOOM.put(14, 1d);
        BAIDU_ZOOM.put(13, 2d);
        BAIDU_ZOOM.put(12, 5d);
        BAIDU_ZOOM.put(11, 10d);
        BAIDU_ZOOM.put(10, 20d);
        BAIDU_ZOOM.put(9, 25d);
        BAIDU_ZOOM.put(8, 50d);
        BAIDU_ZOOM.put(7, 100d);
        BAIDU_ZOOM.put(6, 200d);
        BAIDU_ZOOM.put(5, 500d);
        BAIDU_ZOOM.put(4, 1000d);
        BAIDU_ZOOM.put(3, 2000d);
        BAIDU_ZOOM.put(2, 5000d);
        BAIDU_ZOOM.put(1, 10000d);
    }

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(HouseMapService.class);

    public MapHouseDataResult queryHouseData(Float lng, Float lat, Integer zoom) {
        //1.5倍距离范围，根据实际需求调整
        //111.12 代表 1° = 111.12km
        double distance = BAIDU_ZOOM.get(zoom) * 1.5 / 111.12;
        //near操作查询附近的数据
        //maxDistance控制查询范围 单位是度°
        Query query = Query.query(Criteria.where("loc").near(new Point(lng, lat)).maxDistance(distance));

        List<MongoHouse> mongoHouses = this.mongoTemplate.find(query, MongoHouse.class);

        List<MapHouse> list = new ArrayList<>();
        for (MongoHouse mongoHouse : mongoHouses) {
            list.add(new MapHouse(mongoHouse.getLoc()[0], mongoHouse.getLoc()[1],mongoHouse.getTitle(),mongoHouse.getHid()));
        }
        return new MapHouseDataResult(list,Long.valueOf(list.size()));
    }

    public void addHouseData(String address, Long houseResourcesId,String title) {
        //1.5倍距离范围，根据实际需求调整
        /*
        db.house.insert({hid:1,title:'整租 · 南丹大楼 1居室 7500',loc:
        [121.4482236974557,31.196523937504549]})
         */
        MongoHouse mongoHouse = new MongoHouse();
        mongoHouse.setHid(houseResourcesId);
        Float[] loc = getLoc(address);
        mongoHouse.setLoc(loc);
        mongoHouse.setTitle(title);
        MongoHouse save = this.mongoTemplate.save(mongoHouse);
        System.out.println(save);
    }
    public Float[] getLoc(String address){
        String locationVo = restTemplate.getForObject("http://api.map.baidu.com/geocoder/v2/?address=" + address + "&ak=jpfEH2etB2gutGyHpxVdWy8ZrTxbu0qj&output=json", String.class);
        Map map = JSONObject.parseObject(locationVo, Map.class);
        Object result = map.get("result");
        if (null != result){
            Map map1 = JSON.parseObject(result.toString(), Map.class);
            Object location = map1.get("location");
            Map map2 = JSON.parseObject(location.toString(), Map.class);
            String lat = map2.get("lat").toString();
            Float latF = Float.valueOf(lat);
            String lng = map2.get("lng").toString();
            Float lngF = Float.valueOf(lng);
            Float[] loc = new Float[2];
            loc[0] = lngF;
            loc[1] = latF;
            return loc;
        }
        logger.error("获取地址经纬度出错");
        return null;
    }
}