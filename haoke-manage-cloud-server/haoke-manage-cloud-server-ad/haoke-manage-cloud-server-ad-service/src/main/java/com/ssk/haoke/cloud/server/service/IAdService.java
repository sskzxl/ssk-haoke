package com.ssk.haoke.cloud.server.service;

import com.ssk.haoke.cloud.server.pojo.Ad;
import com.ssk.haoke.cloud.server.vo.PageInfo;

public interface IAdService{
    PageInfo<Ad> queryAdList(Ad ad, Integer page, Integer pageSize);
}