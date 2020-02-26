package com.ssk.haoke.cloud.server.ad.rest;

import com.ssk.haoke.cloud.server.ad.api.IAdApi;
import com.ssk.haoke.cloud.server.ad.pojo.Ad;
import com.ssk.haoke.cloud.server.house.eo.PageInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("v1/house/ad")
public class AdRest implements IAdApi {
    @Resource
    private IAdApi adApi;
    @Override
    public PageInfo<Ad> queryAdList(Integer type, Integer page, Integer pageSize) {
        return adApi.queryAdList(type,page,pageSize);
    }
}
