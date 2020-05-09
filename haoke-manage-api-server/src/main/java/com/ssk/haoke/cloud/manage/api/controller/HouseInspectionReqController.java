package com.ssk.haoke.cloud.manage.api.controller;

import com.ssk.haoke.cloud.manage.api.service.impl.HouseInspectionReqServiceImpl;
import com.ssk.haoke.cloud.server.house.api.dto.request.HouseInspectionReqReqDto;
import com.ssk.haoke.cloud.server.house.api.dto.response.HouseInspectionRespDto;
import com.ssk.haoke.cloud.server.house.rest.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("house/inspection")
@CrossOrigin
@Api(tags = "好客租房-管理应用", description = "看房管理")
public class HouseInspectionReqController {
    @Resource
    private HouseInspectionReqServiceImpl inspectionReqService;

    /**
     * 新增房源
     *
     * @return
     */
    @PostMapping
    @ApiOperation(value = "新增房源")
    public RestResponse<Long> save(@RequestBody HouseInspectionReqReqDto houseInspectionReqDto) {
        System.out.println(houseInspectionReqDto);
        RestResponse<Long> save = this.inspectionReqService.save(houseInspectionReqDto);
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
    public RestResponse<List<Map<String,String>>> list(@RequestParam(name = "filter")String filter,
                                                       @RequestParam(name = "pageNum",
                                                                     defaultValue = "1") Integer pageNum,
                                                       @RequestParam(name = "pageSize",
                                                                     defaultValue = "10") Integer pageSize) {
        return this.inspectionReqService.queryList(pageNum, pageSize, filter);
    }

    /**
     * 修改房源
     *
     * @return
     */
    @PutMapping
    @ApiOperation(value = "更新房源")
    public RestResponse<Void> update(@RequestBody HouseInspectionReqReqDto houseInspectionReqDto) {

        return this.inspectionReqService.update(houseInspectionReqDto);


    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "根据id删除房源")
    public RestResponse delete(@PathVariable("id") String ids) {
        System.out.println("进来了吗");

        RestResponse<Void> response = this.inspectionReqService.delete(ids);
        return response;
    }

    @GetMapping("{id}")
    @ApiOperation(value = "根据id获取房源")
    public RestResponse<HouseInspectionRespDto> get(@PathVariable("id") Long id) {
        return inspectionReqService.queryById(id);
    }
}
