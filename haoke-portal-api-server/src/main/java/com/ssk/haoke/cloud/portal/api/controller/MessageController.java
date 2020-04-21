package com.ssk.haoke.cloud.portal.api.controller;

import com.ssk.haoke.cloud.portal.api.pojo.Message;
import com.ssk.haoke.cloud.portal.api.service.impl.ImUserService;
import com.ssk.haoke.cloud.portal.api.service.impl.MessageService;
import com.ssk.haoke.cloud.server.house.rest.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("v1/house/message")
@Api(tags = "聊天应用")
public class MessageController{

    @Resource
    private ImUserService userService;
    @Resource
    private MessageService messageService;

    /**
     *
     * @param fromId
     * @param toId
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "聊天记录列表",notes = "获取当前用户（fromId）与好友（toId）的聊天记录")
    public RestResponse<List<Message>> queryMessageList(@RequestParam("fromId") Long fromId,
                                                        @RequestParam("toId") Long toId,
                                                        @RequestParam(value = "page",
                                                  defaultValue = "1") Integer page,
                                                        @RequestParam(value = "rows",
                                                  defaultValue = "100") Integer rows){
            return this.messageService.queryMessageList(fromId,toId,page,rows);
    }
    @RequestMapping(value = "user/list",method = RequestMethod.GET)
    @ApiOperation(value = "聊天用户列表",notes = "获取当前用户聊天过的好友")
    public RestResponse<List<Map<String, Object>>> queryUserList(@RequestParam("fromId") Long fromId){
        return userService.queryUserList(fromId);
    }

    @RequestMapping(value = "user/contact",method = RequestMethod.GET)
    @ApiOperation(value = "联系房东",notes = "获取当前用户聊天过的好友")
    public RestResponse<Void> contactHost(@RequestParam("fromId") Long fromId,@RequestParam("toId")Long toId){
        return userService.contactHost(fromId,toId);
    }
}
