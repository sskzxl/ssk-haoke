package com.ssk.haoke.im.controller;

import com.ssk.haoke.im.pojo.Message;
import com.ssk.haoke.im.pojo.User;
import com.ssk.haoke.im.pojo.UserData;
import com.ssk.haoke.im.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {
    @Autowired
    private MessageService messageService;
    //拉去用户列表（模拟实现）
    @GetMapping
    public List<Map<String, Object>> queryUserList(@RequestParam("fromId") Long fromId){
        List<Map<String,Object>> result = new ArrayList<>();
        //遍历所有键值对
        for(Map.Entry<Long,User> userEntry : UserData.USER_MAP.entrySet()){
            HashMap<String, Object> map = new HashMap<>();
            map.put("id",userEntry.getValue().getId());
            map.put("avatar","http://image.haoke.com/images/headPicture/1.jpg");
            map.put("fromId",fromId);
            map.put("info_type", null);
            map.put("to_user", map.get("id"));
            map.put("username", userEntry.getValue().getUsername());

            // 获取最后一条消息 todo
           Message message = this.messageService.findNearestMessage(fromId,(Long)map.get("id"));
            if (message != null) {
                map.put("chat_msg", message.getMsg());
                map.put("chat_time", message.getSendDate().getTime());
            }
            result.add(map);
        }
        return result;
    }

}
