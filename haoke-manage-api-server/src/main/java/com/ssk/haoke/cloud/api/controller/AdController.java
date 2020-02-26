package com.ssk.haoke.cloud.api.controller;

import com.ssk.haoke.cloud.api.service.IAdService;
import com.ssk.haoke.cloud.api.vo.WebResult;
import com.ssk.haoke.cloud.server.ad.pojo.Ad;
import com.ssk.haoke.cloud.server.house.eo.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("ad")
@RestController
@CrossOrigin
public class AdController {
    @Autowired
    IAdService IAdService;

    /**
     * 首页广告位
     * @return
     */
    @GetMapping
    public WebResult queryIndexAd(){
        PageInfo<Ad> pageInfo = this.IAdService.queryAdList(1,1,4);
        //所有广告对象
        List<Ad> records = pageInfo.getRecords();
        //根据前端要求的数据封装数据
        List<Object> result = new ArrayList<>();
        for (Ad ad : records) {
            //把所有的
            Map<String,Object> map = new HashMap<>();
            map.put("original",ad.getUrl());
            result.add(map);
        }
        return WebResult.ok(result);
    }
}
