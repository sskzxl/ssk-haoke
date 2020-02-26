package com.ssk.haoke.cloud.server.ad.apiimpl;

import com.ssk.haoke.cloud.server.ad.service.IAdService;
import com.ssk.haoke.cloud.server.ad.api.IAdApi;
import com.ssk.haoke.cloud.server.ad.pojo.Ad;
import com.ssk.haoke.cloud.server.house.eo.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("adApi")
public class AdApiImpl implements IAdApi {
    @Autowired
    private IAdService adService;
    @Override
    public PageInfo<Ad> queryAdList(Integer type, Integer page, Integer pageSize) {
        Ad ad = new Ad();
        ad.setType(type);
        return this.adService.queryAdList(ad, page, pageSize);
    }
}