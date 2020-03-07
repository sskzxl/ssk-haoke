package com.ssk.haoke.cloud.manage.api.controller;

import com.ssk.haoke.cloud.manage.api.service.impl.HouseRentMgmtServiceImpl;
import com.ssk.haoke.cloud.server.house.api.dto.request.HouseRentMgmtReqDto;
import com.ssk.haoke.cloud.server.house.api.dto.response.HouseRentMgmtDetailRespDto;
import com.ssk.haoke.cloud.server.house.api.dto.response.HouseRentMgmtRespDto;
import com.ssk.haoke.cloud.server.house.eo.PageInfo;
import com.ssk.haoke.cloud.server.house.rest.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("house/rent")
@CrossOrigin
@Api(tags = "好客租房-管理应用", description = "租房管理")
public class HouseRentMgmtController {
    @Autowired
    public static final Logger LOGGER = LoggerFactory.getLogger(HouseRentMgmtController.class);
    @Autowired
    private HouseRentMgmtServiceImpl houseRentMgmtService;

    /**
     * 新增房源
     *
     * @return
     */
    @PostMapping
    @ApiOperation(value = "新增房源")
    public RestResponse<Long> save(@RequestBody HouseRentMgmtReqDto houseRentMgmtReqDto) {
        System.out.println(houseRentMgmtReqDto);
        RestResponse<Long> save = this.houseRentMgmtService.save(houseRentMgmtReqDto);
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
    public RestResponse<PageInfo<HouseRentMgmtRespDto>> list(@RequestParam(name = "filter")String filter,
                                                             @RequestParam(name = "pageNum",
                                                                      defaultValue = "1") Integer pageNum,
                                                             @RequestParam(name = "pageSize",
                                                                      defaultValue = "10") Integer pageSize) {
        return this.houseRentMgmtService.queryList(pageNum, pageSize, filter);
    }

    /**
     * 修改房源
     *
     * @return
     */
    @PutMapping
    @ApiOperation(value = "更新房源")
    public RestResponse<Void> update(@RequestBody HouseRentMgmtReqDto houseRentMgmtReqDto) {

        return this.houseRentMgmtService.update(houseRentMgmtReqDto);


    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "根据id删除房源")
    public RestResponse delete(@PathVariable("id") String ids) {
        System.out.println("进来了吗");

        RestResponse<Void> response = this.houseRentMgmtService.delete(ids);
        return response;
    }

    @GetMapping("{id}")
    @ApiOperation(value = "根据id获取房源")
    public RestResponse<HouseRentMgmtDetailRespDto> get(@PathVariable("id") Long id) {
        return houseRentMgmtService.queryById(id);
    }

}