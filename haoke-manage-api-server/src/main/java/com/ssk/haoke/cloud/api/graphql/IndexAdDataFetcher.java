package com.ssk.haoke.cloud.api.graphql;

import com.ssk.haoke.cloud.api.service.IAdService;
import com.ssk.haoke.cloud.api.vo.ad.index.IndexAdResult;
import com.ssk.haoke.cloud.api.vo.ad.index.IndexAdResultData;
import com.ssk.haoke.cloud.server.ad.pojo.Ad;
import com.ssk.haoke.cloud.server.house.eo.PageInfo;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class IndexAdDataFetcher implements MyDataFetcher {
    @Autowired
    private IAdService IAdService;
    @Override
    public String fieldName() {
        return "IndexAdList";
    }

    @Override
    public Object dataFetcher(DataFetchingEnvironment environment) {
        PageInfo<Ad> pageInfo = this.IAdService.queryAdList(1, 1, 3);
        List<Ad> ads = pageInfo.getRecords();
        List<IndexAdResultData> list = new ArrayList<>();

        for (Ad ad : ads) {
            list.add(new IndexAdResultData(ad.getUrl()));
        }

        return new IndexAdResult(list);
    }
}