package com.ssk.haoke.cloud.server.api;

import com.ssk.haoke.cloud.server.api.dto.request.HouseRentMgmtReqDto;
import com.ssk.haoke.cloud.server.rest.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
* 租房管理表服务接口
*
* @author 代码生成器
 **/

 @Api(tags = "好客租房管理平台：房源中心")
@FeignClient(name = "haoke-manage-cloud-server-house-resources", path = "/v1/houserent",  url = "${ssk-haoke-houseResources-api:}")
public interface IHouseRentMgmtApi {

    /**
    * 新增租房管理表
    *
    * @param addReqDto 租房管理表请求对象
    * @return 处理结果
    */
    @PostMapping("")
    @ApiOperation(value = "新增租房管理表", notes = "新增租房管理表")
    RestResponse<Long> addRentManagement(@RequestBody HouseRentMgmtReqDto addReqDto);

    /**
    * 修改租房管理表
    *
    * @param modifyReqDto 租房管理表请求对象
    * @return 处理结果
    */
    @PutMapping("")
    @ApiOperation(value = "修改租房管理表", notes = "修改租房管理表")
    RestResponse<Void> modifyRentManagement(@RequestBody HouseRentMgmtReqDto modifyReqDto);

    /**
    * 删除租房管理表
    *
    * @param ids        租房管理表删除数据ID
    * @return 处理结果
    */
    @DeleteMapping("/{ids}")
    @ApiOperation(value = "删除租房管理表", notes = "删除租房管理表")
    RestResponse<Void> removeRentManagement(@PathVariable("ids") String ids);
}
