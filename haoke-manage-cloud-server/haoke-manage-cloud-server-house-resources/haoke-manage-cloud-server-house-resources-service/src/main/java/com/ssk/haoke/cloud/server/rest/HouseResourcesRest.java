package com.ssk.haoke.cloud.server.rest;

import com.ssk.haoke.cloud.server.api.IHouseResourcesApi;
import com.ssk.haoke.cloud.server.pojo.HouseResources;
import com.ssk.haoke.cloud.server.vo.PageInfo;
import com.ssk.haoke.cloud.server.vo.TableResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/v1/house/resources")
public class HouseResourcesRest implements IHouseResourcesApi {
    @Resource
    private IHouseResourcesApi houseResourcesApi;
    @Override
    public boolean deleteHouseResource(Long id) {
        return houseResourcesApi.deleteHouseResource(id);
    }

    @Override
    public int saveHouseResources(HouseResources houseResources) {
        return houseResourcesApi.saveHouseResources(houseResources);
    }

    @Override
    public PageInfo<HouseResources> queryHouseResourcesList(Integer page, Integer pageSize) {
        return houseResourcesApi.queryHouseResourcesList(page,pageSize);
    }

    @Override
    public HouseResources queryHouseResourcesById(Long id) {
        return houseResourcesApi.queryHouseResourcesById(id);
    }

    @Override
    public boolean updateHouseResources(HouseResources houseResources) {
        return houseResourcesApi.updateHouseResources(houseResources);
    }
}
