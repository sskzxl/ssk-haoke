package com.ssk.haoke.cloud.api.service;

import com.ssk.haoke.cloud.server.ad.pojo.Ad;
import com.ssk.haoke.cloud.server.house.eo.PageInfo;

public interface IAdService {
    public PageInfo<Ad> queryAdList(Integer type, Integer page, Integer pageSize);
}
