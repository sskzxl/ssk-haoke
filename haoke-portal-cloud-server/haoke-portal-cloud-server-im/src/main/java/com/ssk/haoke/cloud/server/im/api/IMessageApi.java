package com.ssk.haoke.cloud.server.im.api;

import com.ssk.haoke.cloud.server.house.rest.RestResponse;
import com.ssk.haoke.cloud.server.im.pojo.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "haoke-portal-cloud-server-im",
        path = "/v1/message",
        url = "${haoke.portal.center.message.api:}")
@Api(tags = "聊天中心")
public interface IMessageApi {
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
                                                                defaultValue = "100") Integer rows);

    @RequestMapping(value = "user/list",method = RequestMethod.GET)
    @ApiOperation(value = "聊天用户列表",notes = "获取当前用户聊天过的好友")
    public RestResponse<List<Map<String, Object>>> queryUserList(@RequestParam("fromId") Long fromId);
}
