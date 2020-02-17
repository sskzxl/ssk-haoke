package com.ssk.haoke.cloud.api.graphql;

import com.ssk.haoke.cloud.api.service.IHouseResourcesService;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component //加入到Spring容器
public class HouseResourcesListDataFetcher implements MyDataFetcher {
    @Autowired
    private IHouseResourcesService houseResourcesService;

    public String fieldName() {
        return "HouseResourcesList";
    }

    @Override
    public Object dataFetcher(DataFetchingEnvironment environment) {
        Integer page = environment.getArgument("page");
        if (page == null) {
            page = 1;
        }
        Integer pageSize = environment.getArgument("pageSize");
        if (pageSize == null) {
            pageSize = 5;
        }
        return this.houseResourcesService.queryList( page, pageSize,null);
    }
}