package com.ssk.haoke.cloud.server.im.service;

import com.ssk.haoke.cloud.server.house.service.BaseServiceImpl;
import com.ssk.haoke.cloud.server.im.eo.ImEo;
import com.ssk.haoke.cloud.server.im.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("imUserService")
public class ImUserService extends BaseServiceImpl<ImEo>{
    @Autowired
    private MessageService messageService;

    public List<Map<String, Object>> queryUserList(Long fromId){
        List<Map<String,Object>> result = new ArrayList<>();
        if (null == fromId) return null;
        ImEo imEo = new ImEo();
        imEo.setFromId(fromId);
        List<ImEo> imEos = super.queryListByWhere(imEo);
        if (!CollectionUtils.isEmpty(imEos)){
            for (ImEo eo : imEos) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("id",eo.getId());
                map.put("avatar","http://image.haoke.com/images/headPicture/1.jpg");
                map.put("from_user",fromId);
                map.put("to_user", eo.getToId());
                map.put("from_username", eo.getToUsername());
                map.put("to_username", eo.getToUsername());
                map.put("info_type", null);
                // 获取最后一条消息 todo
                Message message = this.messageService.findNearestMessage(fromId,eo.getToId());
                if (message != null) {
                    map.put("chat_msg", message.getMsg());
                    map.put("chat_time", message.getSendDate().getTime());
                }
                result.add(map);
            }

        }
        return result;
    }
}





