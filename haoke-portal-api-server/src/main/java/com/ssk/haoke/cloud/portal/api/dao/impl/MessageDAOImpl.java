package com.ssk.haoke.cloud.portal.api.dao.impl;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.ssk.haoke.cloud.portal.api.dao.MessageDAO;
import com.ssk.haoke.cloud.portal.api.pojo.Message;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class MessageDAOImpl implements MessageDAO {
    @Autowired
    MongoTemplate mongoTemplate;

    public List<Message> findListByFromAndTo(Long fromId, Long toId, Integer page, Integer rows) {
        //消息由fromId发给toId的
        Criteria criteriaFrom = Criteria.where("from.id").is(fromId).and("to.id").is(toId);
        //消息由toId发给fromId的
        Criteria criteriaTo = Criteria.where("to.id").is(fromId).and("from.id").is(toId);
        //消息由fromId发给toId  或 由toId发给fromId
        Criteria criteria = new Criteria().orOperator(criteriaFrom,criteriaTo);
        //分页由第0页开始
        PageRequest pageRequest  = PageRequest.of(page-1,rows, Sort.Direction.ASC,"send_date");

        Query query = Query.query(criteria).with(pageRequest);
        System.out.println(query);
        return mongoTemplate.find(query,Message.class);
    }

    @Override
    public Message findMessageById(String id) {
        return this.mongoTemplate.findById(new ObjectId(id), Message.class);
    }
    //查找最新的消息
    public Message findNearestMessage(Long fromId, Long toId){
        //消息由fromId发给toId的
        Criteria criteriaFrom = Criteria.where("from.id").is(fromId).and("to.id").is(toId);
        //消息由toId发给fromId的
        Criteria criteriaTo = Criteria.where("to.id").is(fromId).and("from.id").is(toId);
        //消息由fromId发给toId  或 由toId发给fromId
        Criteria criteria = new Criteria().orOperator(criteriaFrom,criteriaTo);
        Query query = Query.query(criteria).with(PageRequest.of(0,1,Sort.Direction.DESC,"send_date"));
        return this.mongoTemplate.findOne(query,Message.class);
    }

    @Override
    public UpdateResult updateMessageState(ObjectId id, Integer status) {
        Query query = Query.query(Criteria.where("id").is(id));
        Update update = Update.update("status", status);
        //消息为未读时更新消息发送时间
        if (status.intValue() == 1) {
            update.set("send_date", new Date());
            //更新已读时间
        } else if (status.intValue() == 2) {
            update.set("read_date", new Date());
        }
        return this.mongoTemplate.updateFirst(query, update, Message.class);
    }

    @Override
    public Message saveMessage(Message message) {
        message.setId(ObjectId.get());
        message.setSendDate(new Date());
        message.setStatus(1);
        return this.mongoTemplate.save(message);
    }

    @Override
    public DeleteResult deleteMessage(String id) {
        Query query = Query.query(Criteria.where("id").is(id));
        return this.mongoTemplate.remove(query, Message.class);
    }
}
