package com.ssk.haoke.cloud.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ssk.haoke.cloud.server.pojo.Ad;
import com.ssk.haoke.cloud.server.service.IAdService;
import com.ssk.haoke.cloud.server.service.BaseServiceImpl;
import com.ssk.haoke.cloud.server.vo.PageInfo;
import org.springframework.stereotype.Service;

@Service
public class AdServiceImpl extends BaseServiceImpl implements IAdService{
    @Override
    public PageInfo<Ad> queryAdList(Ad ad, Integer page, Integer pageSize) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("updated");
        queryWrapper.eq("type",ad.getType());
        IPage iPage = super.queryPageList(queryWrapper, page, pageSize);

        return new PageInfo<Ad>(Long.valueOf(iPage.getTotal()).intValue(),page,pageSize,iPage.getRecords());
    }
}
