package com.ssk.haoke.cloud.api.controller;

import com.ssk.haoke.cloud.api.service.IUserService;
import com.ssk.haoke.cloud.server.house.eo.PageInfo;
import com.ssk.haoke.cloud.server.house.rest.RestResponse;
import com.ssk.haoke.cloud.server.user.api.dto.request.UserReqDto;
import com.ssk.haoke.cloud.server.user.api.dto.response.UserRespDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("house/user")
@CrossOrigin
@Api(tags = "好客租房-管理应用", description = "用户管理")
public class UserController {
    @Resource
    private IUserService userService;
    @Autowired
    public static final Logger LOGGER = LoggerFactory.getLogger(HouseResourcesController.class);


    /**
     * 新增房源
     *
     * @return
     */
    @PostMapping
    @ApiOperation(value = "新增用户")
    public RestResponse<Long> save(@RequestBody UserReqDto userReqDto) {
        System.out.println(userReqDto);
        return new RestResponse<>(userService.addUser(userReqDto));
    }

    /**
     * 查询房源列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping
    @ApiOperation(value = "查询用户列表")
    public RestResponse<PageInfo<UserRespDto>> list(@RequestParam(name = "filter")String filter,
                                                    @RequestParam(name = "pageNum",
                                                                      defaultValue = "1") Integer pageNum,
                                                    @RequestParam(name = "pageSize",
                                                                      defaultValue = "10") Integer pageSize) {
        return new RestResponse<>(this.userService.queryByPage(filter,pageNum, pageSize));
    }

    /**
     * 修改房源
     *
     * @return
     */
    @PutMapping
    @ApiOperation(value = "更新用户")
    public RestResponse<Void> update(@RequestBody UserReqDto userReqDto) {
        this.userService.modifyUser(userReqDto);
        return new RestResponse<Void>();
    }

    @DeleteMapping("{ids}")
    @ApiOperation(value = "根据ids删除用户,ids")
    public RestResponse delete(@PathVariable("ids") String ids) {
        System.out.println("进来了吗");
        this.userService.removeUser(ids);
        return RestResponse.SUCCEED;
    }

    @GetMapping("{id}")
    @ApiOperation(value = "根据id获取用户")
    public RestResponse<UserRespDto> get(@PathVariable("id") Long id) {
        return new RestResponse<>(userService.queryById(id));
    }

}
