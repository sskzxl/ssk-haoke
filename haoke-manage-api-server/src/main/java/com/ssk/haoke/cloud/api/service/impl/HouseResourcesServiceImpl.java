package com.ssk.haoke.cloud.api.service.impl;

import com.ssk.haoke.cloud.api.service.IHouseResourcesService;
import com.ssk.haoke.cloud.server.api.IHouseResourcesApi;
import com.ssk.haoke.cloud.server.pojo.HouseResources;
import com.ssk.haoke.cloud.server.vo.PageInfo;
import com.ssk.haoke.cloud.server.vo.Pagination;
import com.ssk.haoke.cloud.server.vo.TableResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HouseResourcesServiceImpl implements IHouseResourcesService {
    @Autowired(required = false)
    private IHouseResourcesApi houseResourcesApi;

    //删除房源
    public boolean delete(Long id){
        return this.houseResourcesApi.deleteHouseResource(id);
    }

    //增加房源
    public boolean save(HouseResources houseResources) {
        int result =
                this.houseResourcesApi.saveHouseResources(houseResources);
        return result == 1;
    }
    //查询房源列表
    public TableResult<HouseResources> queryList(Integer pageNum, Integer pageSize, HouseResources houseResources) {
        PageInfo<HouseResources> result = this.houseResourcesApi.queryHouseResourcesList(pageNum, pageSize);
        return new TableResult<>(result.getRecords(),
                new Pagination(pageNum,pageSize,result.getTotal()));
    }

    /**
     * @param id
     * @return
     */
    public HouseResources queryById(Long id){
        return this.houseResourcesApi.queryHouseResourcesById(id);
    }

    /*
        更新房源
     */
    public boolean update(HouseResources houseResources) {
        return this.houseResourcesApi.updateHouseResources(houseResources);
    }



}