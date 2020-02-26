package com.ssk.haoke.cloud.server.ad.service;

import com.ssk.haoke.cloud.server.ad.pojo.Ad;
import com.ssk.haoke.cloud.server.house.eo.PageInfo;

public interface IAdService{
    PageInfo<Ad> queryAdList(Ad ad, Integer page, Integer pageSize);
}