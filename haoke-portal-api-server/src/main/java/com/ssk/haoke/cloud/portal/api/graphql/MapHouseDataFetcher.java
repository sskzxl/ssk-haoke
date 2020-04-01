package com.ssk.haoke.cloud.portal.api.graphql;

import com.ssk.haoke.cloud.portal.api.service.impl.HouseMapService;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapHouseDataFetcher implements MyDataFetcher {
        @Autowired
        private HouseMapService houseMapService;
        @Override
        public String fieldName() {
                return "MapHouseData";
        }
        @Override
        public Object dataFetcher(DataFetchingEnvironment environment) {
                Float lng = ((Double)environment.getArgument("lng")).floatValue();
                Float lat = ((Double)environment.getArgument("lat")).floatValue();
                Integer zoom = environment.getArgument("zoom");
                System.out.println("lng->" + lng + ",lat->" + lat + ",zoom->" + zoom);
                return this.houseMapService.queryHouseData(lng,lat,zoom);
        }

}