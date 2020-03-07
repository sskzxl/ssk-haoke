package com.ssk.haoke.cloud.portal.api.graphql;

import com.ssk.haoke.cloud.server.house.api.query.IHouseResourcesQueryApi;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component //加入到Spring容器
public class HouseResourcesDataFetcher implements MyDataFetcher {
    @Autowired
    private IHouseResourcesQueryApi houseResourcesQueryApi;

    public String fieldName() {
        return "HouseResources";
    }

    public Object dataFetcher(DataFetchingEnvironment environment) {
        Long id = environment.getArgument("id");
        return this.houseResourcesQueryApi.queryHouseResourcesById(id).getData();
    }
}