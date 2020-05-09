package com.ssk.haoke.cloud.portal.api.controller;

import com.ssk.haoke.cloud.portal.api.service.impl.HouseResourcesServiceImpl;
import com.ssk.haoke.cloud.server.house.api.dto.request.HouseResourcesReqDto;
import com.ssk.haoke.cloud.server.house.api.dto.response.DropDownRespDto;
import com.ssk.haoke.cloud.server.house.api.dto.response.HouseResourcesListRespDto;
import com.ssk.haoke.cloud.server.house.api.dto.response.HouseResourcesRespDto;
import com.ssk.haoke.cloud.server.house.eo.PageInfo;
import com.ssk.haoke.cloud.server.house.rest.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("house/resources")
@CrossOrigin
@Api(tags = "好客租房-前台应用", description = "房源中心")
public class HouseResourcesController {
    @Autowired
    public static final Logger LOGGER = LoggerFactory.getLogger(HouseResourcesController.class);
    @Autowired
    private HouseResourcesServiceImpl houseResourcesService;


    /**
     * 新增房源
     *
     * @return
     */
    @PostMapping
    @ApiOperation(value = "新增房源")
    public RestResponse<Long> save(@RequestBody @Validated HouseResourcesReqDto houseResourcesReqDto) {
        System.out.println(houseResourcesReqDto);
        return this.houseResourcesService.save(houseResourcesReqDto);
    }

    /**
     * 查询房源列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping
    @ApiOperation(value = "查询房源列表")
    public RestResponse<PageInfo<HouseResourcesListRespDto>> list(@RequestParam(name = "filter")String filter,
                                                                          @RequestParam(name = "pageNum",
                                                                      defaultValue = "1") Integer pageNum,
                                                                          @RequestParam(name = "pageSize",
                                                                      defaultValue = "10") Integer pageSize) {
        return this.houseResourcesService.queryList(pageNum, pageSize, filter);
    }

    /**
     * 修改房源
     *
     * @return
     */
    @PutMapping
    @ApiOperation(value = "更新房源")
    public RestResponse<Boolean> update(@RequestBody HouseResourcesReqDto houseResourcesReqDto) {

        return this.houseResourcesService.update(houseResourcesReqDto);


    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "根据id删除房源")
    public RestResponse delete(@PathVariable("id") Long id) {
        System.out.println("进来了吗");

        RestResponse<Boolean> response = this.houseResourcesService.delete(id);
        return response;
    }

    @GetMapping("{id}")
    @ApiOperation(value = "根据id获取房源")
    public RestResponse<HouseResourcesRespDto> get(@PathVariable("id") Long id) {
        RestResponse<HouseResourcesRespDto> response = houseResourcesService.queryById(id);
        HouseResourcesRespDto data = response.getData();
        if (null != data){
            String pic = data.getPic();
            if (StringUtils.isNotBlank(pic)){
                String[] picArr = pic.split(";");
                List list = CollectionUtils.arrayToList(picArr);
                data.setPicList(list);
                response.setData(data);
            }
            return response;
        }
        return RestResponse.FAIL;
    }
    @GetMapping("/cityName/{cityName}")
    @ApiOperation(value = "根据地区查询房源")
    public RestResponse<PageInfo<HouseResourcesRespDto>> getPageByCity(@PathVariable("cityName")String cityName,
                                                                       @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                                                       @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize) {
        return houseResourcesService.getPageByCity(cityName, pageNum, pageSize);
    }
    @GetMapping("/allCity")
    @ResponseBody
    @ApiOperation(value = "查询城市下拉列表",notes = "查询城市下拉列表")
    RestResponse<List<DropDownRespDto>> getAllCity(){
        return houseResourcesService.getAllCity();
    }

    @PutMapping("review")
    @ApiOperation(value = "审核房源,后端同步数据到es和mongo",notes = "审核房源")
    public RestResponse sysnHouseData(List<Long> ids){
        return this.houseResourcesService.sysnHouseData(ids);
    }
}