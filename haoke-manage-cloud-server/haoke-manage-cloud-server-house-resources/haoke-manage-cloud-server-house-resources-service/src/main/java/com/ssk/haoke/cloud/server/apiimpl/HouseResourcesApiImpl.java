package com.ssk.haoke.cloud.server.apiimpl;

import com.ssk.haoke.cloud.server.api.IHouseResourcesApi;
import com.ssk.haoke.cloud.server.pojo.HouseResources;
import com.ssk.haoke.cloud.server.service.HouseResourcesService;
import com.ssk.haoke.cloud.server.vo.PageInfo;
import com.ssk.haoke.cloud.server.vo.TableResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("houseResourcesApi")
public class HouseResourcesApiImpl implements IHouseResourcesApi {

    @Autowired
    private HouseResourcesService houseResourcesService;

    @Override
    public boolean deleteHouseResource(Long id) {
        return houseResourcesService.deleteHouseResources(id);
    }

    @Override
    public int saveHouseResources(HouseResources houseResources) {
        return this.houseResourcesService.saveHouseResources(houseResources);
    }

    @Override
    public PageInfo<HouseResources> queryHouseResourcesList(Integer page, Integer pageSize) {
        return this.houseResourcesService.queryHouseResourcesList(page,pageSize);
    }

    @Override
    public HouseResources queryHouseResourcesById(Long id) {
        return this.houseResourcesService.queryHouseResourcesById(id);
    }
    @Override
    public boolean updateHouseResources(HouseResources houseResources) {
        return this.houseResourcesService.updateHouseResources(houseResources);
    }

}