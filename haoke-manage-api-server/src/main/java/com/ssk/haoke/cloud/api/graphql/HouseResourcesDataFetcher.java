package com.ssk.haoke.cloud.api.graphql;

import com.ssk.haoke.cloud.api.service.IHouseResourcesService;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component //加入到Spring容器
public class HouseResourcesDataFetcher implements MyDataFetcher {
    @Autowired
    private IHouseResourcesService houseResourcesService;

    public String fieldName() {
        return "HouseResources";
    }

    public Object dataFetcher(DataFetchingEnvironment environment) {
        Long id = environment.getArgument("id");
        return this.houseResourcesService.queryById(id);
    }
}