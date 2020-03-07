package com.ssk.haoke.cloud.portal.api.service.impl;

import com.ssk.haoke.cloud.portal.api.service.IAdService;
import com.ssk.haoke.cloud.server.ad.api.IAdApi;
import com.ssk.haoke.cloud.server.ad.pojo.Ad;
import com.ssk.haoke.cloud.server.house.eo.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("IAdService")
public class AdServiceImpl implements IAdService {
    @Autowired
    private IAdApi adApi;
    public PageInfo<Ad> queryAdList(Integer type, Integer page, Integer pageSize) {
        PageInfo<Ad> adPageInfo = this.adApi.queryAdList(type, page, pageSize);
        return adPageInfo;
    }
}
