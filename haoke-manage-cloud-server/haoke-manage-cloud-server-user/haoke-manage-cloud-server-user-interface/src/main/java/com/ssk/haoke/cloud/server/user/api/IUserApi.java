package com.ssk.haoke.cloud.server.user.api;

import com.ssk.haoke.cloud.server.user.api.dto.request.UserReqDto;
import com.ssk.haoke.cloud.server.house.rest.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
* 服务接口
*
* @author 代码生成器
*/
@Api(tags = {"好客租房管理平台：用户中心"})
@FeignClient(name = "haoke-manage-cloud-server-user", path = "/v1/user", url = "${ssk-haoke-user-api:}")
public interface IUserApi {

    /**
    * 新增
    *
    * @param addReqDto 请求对象
    * @return 处理结果
    */
    @PostMapping("")
    @ApiOperation(value = "新增", notes = "新增")
    RestResponse<Long> addUser(@RequestBody UserReqDto addReqDto);

    /**
    * 修改
    *
    * @param modifyReqDto 请求对象
    * @return 处理结果
    */
    @PutMapping("")
    @ApiOperation(value = "修改", notes = "修改")
    RestResponse<Void> modifyUser(@RequestBody UserReqDto modifyReqDto);

    /**
    * 删除
    *
    * @param ids        删除数据ID
    * @return 处理结果
    */
    @DeleteMapping("/{ids}")
    @ApiOperation(value = "删除", notes = "删除")
    RestResponse<Void> removeUser(@PathVariable("ids") String ids);
}
