package com.ssk.haoke.cloud.server.apiimpl;

import com.ssk.haoke.cloud.server.api.IAdApi;
import com.ssk.haoke.cloud.server.pojo.Ad;
import com.ssk.haoke.cloud.server.service.IAdService;
import com.ssk.haoke.cloud.server.vo.PageInfo;
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