package com.ssk.haoke.cloud.portal.api.graphql;

import com.ssk.haoke.cloud.server.house.api.query.IHouseResourcesQueryApi;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component //加入到Spring容器
public class HouseResourcesListDataFetcher implements MyDataFetcher {
    @Autowired
    private IHouseResourcesQueryApi houseResourcesQueryApi;

    public String fieldName() {
        return "HouseResourcesList";
    }

    @Override
    public Object dataFetcher(DataFetchingEnvironment environment) {
        Integer page = environment.getArgument("pageNum");
        if (page == null) {
            page = 1;
        }
        Integer pageSize = environment.getArgument("pageSize");
        if (pageSize == null) {
            pageSize = 5;
        }
        return this.houseResourcesQueryApi.queryHouseResourcesList( "{}",page, pageSize).getData();
    }
}