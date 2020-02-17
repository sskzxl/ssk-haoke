package com.ssk.haoke.cloud.api.service;

import com.ssk.haoke.cloud.server.pojo.Ad;
import com.ssk.haoke.cloud.server.vo.PageInfo;

public interface IAdService {
    public PageInfo<Ad> queryAdList(Integer type, Integer page, Integer pageSize);
}
