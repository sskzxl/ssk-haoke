package com.ssk.haoke.cloud.portal.api.service.impl;

import com.ssk.haoke.cloud.portal.api.pojo.Message;
import com.ssk.haoke.cloud.portal.api.vo.ImEo;
import com.ssk.haoke.cloud.server.house.rest.RestResponse;
import com.ssk.haoke.cloud.server.house.service.BaseServiceImpl;
import com.ssk.haoke.cloud.server.user.api.dto.response.UserRespDto;
import com.ssk.haoke.cloud.server.user.api.query.IUserQueryApi;
import org.apache.commons.lang3.StringUtils;
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
    @Autowired
    private IUserQueryApi userQueryApi;
    public RestResponse<List<Map<String, Object>>> queryUserList(Long fromId){
        List<Map<String,Object>> result = new ArrayList<>();
        if (null == fromId) return null;
        ImEo imEo = new ImEo();
        imEo.setFromId(fromId);
        List<ImEo> imEos = super.queryListByWhere(imEo);
        if (!CollectionUtils.isEmpty(imEos)){
            for (ImEo eo : imEos) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("id",eo.getId());
                UserRespDto data = userQueryApi.queryById(eo.getToId()).getData();
                String headUrl = data.getHeadUrl();
                if (StringUtils.isNotBlank(headUrl)){
                    map.put("avatar","http://image.haoke.com/head/"+headUrl);
                }else {
                    map.put("avatar","http://image.haoke.com/head/defaultHead.jpg");
                }
                map.put("from_user",fromId);
                map.put("to_user", eo.getToId());
                map.put("from_username", eo.getFromUsername());
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
        return new RestResponse<>(result);
    }

    public RestResponse contactHost(Long fromId, Long toId){
        UserRespDto from = userQueryApi.queryById(fromId).getData();
        UserRespDto to = userQueryApi.queryById(toId).getData();
        if (null == from && null == to){
            throw new RuntimeException("数据有误");
        }
        ImEo imEo = new ImEo();
        imEo.setFromId(from.getId());
        imEo.setToId(to.getId());
        ImEo result = super.queryOne(imEo);
        if (null == result){
            imEo.setFromUsername(from.getUsername());
            imEo.setToUsername(to.getUsername());
            super.save(imEo);
        }else {
            super.update(result);
        }
        return RestResponse.VOID;
    }
}





