package com.ssk.haoke.cloud.api.service.impl;

import com.ssk.haoke.cloud.api.service.IAdService;
import com.ssk.haoke.cloud.server.api.IAdApi;
import com.ssk.haoke.cloud.server.pojo.Ad;
import com.ssk.haoke.cloud.server.vo.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("IAdService")
public class AdServiceImpl implements IAdService {
    @Autowired(required = false)
    private IAdApi adApi;
    public PageInfo<Ad> queryAdList(Integer type, Integer page, Integer pageSize) {
        PageInfo<Ad> adPageInfo = this.adApi.queryAdList(type, page, pageSize);
        return adPageInfo;
    }
}
