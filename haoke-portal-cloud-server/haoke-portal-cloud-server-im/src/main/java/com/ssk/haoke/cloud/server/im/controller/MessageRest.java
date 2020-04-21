package com.ssk.haoke.cloud.server.im.controller;

import com.ssk.haoke.cloud.server.house.rest.RestResponse;
import com.ssk.haoke.cloud.server.im.api.IMessageApi;
import com.ssk.haoke.cloud.server.im.pojo.Message;
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
@RequestMapping("v1/message")
@Api(tags = "聊天应用")
public class MessageRest implements IMessageApi{

    @Resource
    private IMessageApi messageApi;

    /**
     *
     * @param fromId
     * @param toId
     * @param page
     * @param rows
     * @return
     */

    public RestResponse<List<Message>> queryMessageList(@RequestParam("fromId") Long fromId,
                                                       @RequestParam("toId") Long toId,
                                                       @RequestParam(value = "page",
                                                  defaultValue = "1") Integer page,
                                                       @RequestParam(value = "rows",
                                                  defaultValue = "100") Integer rows){
            return this.messageApi.queryMessageList(fromId,toId,page,rows);
    }

    public RestResponse<List<Map<String, Object>>> queryUserList(@RequestParam("fromId") Long fromId){
        return messageApi.queryUserList(fromId);
    }
}
