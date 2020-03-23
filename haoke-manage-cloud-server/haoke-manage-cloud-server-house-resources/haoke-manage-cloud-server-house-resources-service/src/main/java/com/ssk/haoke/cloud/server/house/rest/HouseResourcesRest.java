package com.ssk.haoke.cloud.server.house.rest;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssk.haoke.cloud.server.house.api.IHouseResourcesApi;
import com.ssk.haoke.cloud.server.house.api.dto.request.HouseResourcesReqDto;
import com.ssk.haoke.cloud.server.house.api.dto.response.DropDownRespDto;
import com.ssk.haoke.cloud.server.house.api.dto.response.HouseResourcesRespDto;
import com.ssk.haoke.cloud.server.house.api.query.IHouseResourcesQueryApi;
import com.ssk.haoke.cloud.server.house.eo.PageInfo;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/v1/house/resources")
public class HouseResourcesRest implements IHouseResourcesApi,IHouseResourcesQueryApi {
    @Resource
    private IHouseResourcesApi houseResourcesApi;
    @Resource
    private IHouseResourcesQueryApi houseResourcesQueryApi;
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(HouseResourcesRest.class);

    @Override
    public RestResponse<Boolean> deleteHouseResource(Long id) {
        return houseResourcesApi.deleteHouseResource(id);
    }

    @Override
    public RestResponse<Integer> saveHouseResources(HouseResourcesReqDto houseResourcesReqDto) {
        return houseResourcesApi.saveHouseResources(houseResourcesReqDto);
    }

    @Override
    public RestResponse<PageInfo<HouseResourcesRespDto>> queryHouseResourcesList(String filter,Integer pageNum, Integer pageSize) {

        RestResponse<PageInfo<HouseResourcesRespDto>> response = houseResourcesQueryApi.queryHouseResourcesList(filter, pageNum, pageSize);
        if (null == response.getData()){
            return RestResponse.FAIL;
        }else {
            return response;
        }
    }

    @Override
    public RestResponse<HouseResourcesRespDto> queryHouseResourcesById(Long id) {
        RestResponse<HouseResourcesRespDto> response = houseResourcesQueryApi.queryHouseResourcesById(id);
        if (null == response.getData()){
            return RestResponse.FAIL;
        }
        return response;
    }

    @Override
    public RestResponse<Boolean> updateHouseResources(HouseResourcesReqDto houseResources) {
        return houseResourcesApi.updateHouseResources(houseResources);
    }

    @RequestMapping("/test")
    public void testResource() throws Exception {
//        Request request = new Request("POST", "/haoke/house/_bulk");
        List<String> lines = FileUtils.readLines(new File("E:\\JavaWebLearning\\HaoKe\\template\\data.json"),
                "UTF-8");
        for (String line : lines) {
            LOGGER.info("开始存入数据库");

            OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
            HouseResourcesReqDto houseResourcesReqDto = OBJECT_MAPPER.readValue(line, HouseResourcesReqDto.class);
            String floor = houseResourcesReqDto.getFloor();

            houseResourcesReqDto.setOrientation("南");
            houseResourcesApi.saveHouseResources(houseResourcesReqDto);
            LOGGER.info("存入数据库成功");
        }
    }

    @Override
    public RestResponse<PageInfo<HouseResourcesRespDto>> getPageByCity(String cityName, Integer pageNum, Integer pageSize) {
        return houseResourcesQueryApi.getPageByCity(cityName, pageNum, pageSize);
    }

    @Override
    public RestResponse<List<DropDownRespDto>> getAllCity() {
        return houseResourcesQueryApi.getAllCity();
    }
}
