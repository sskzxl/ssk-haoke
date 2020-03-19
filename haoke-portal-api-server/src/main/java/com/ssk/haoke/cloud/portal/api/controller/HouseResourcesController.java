package com.ssk.haoke.cloud.portal.api.controller;

import com.ssk.haoke.cloud.portal.api.service.impl.HouseResourcesServiceImpl;
import com.ssk.haoke.cloud.server.house.api.dto.request.HouseResourcesReqDto;
import com.ssk.haoke.cloud.server.house.api.dto.response.HouseResourcesRespDto;
import com.ssk.haoke.cloud.server.house.eo.PageInfo;
import com.ssk.haoke.cloud.server.house.rest.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public RestResponse<Boolean> save(@RequestBody HouseResourcesReqDto houseResourcesReqDto) {
        System.out.println(houseResourcesReqDto);
        RestResponse<Boolean> save = this.houseResourcesService.save(houseResourcesReqDto);
        return save;
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
    public RestResponse<PageInfo<HouseResourcesRespDto>> list(@RequestParam(name = "filter")String filter,
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
        return houseResourcesService.queryById(id);
    }

}