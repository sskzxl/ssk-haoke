package com.ssk.haoke.cloud.portal.api.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssk.haoke.cloud.portal.api.vo.HouseData;
import com.ssk.haoke.cloud.portal.api.vo.SearchRespDto;
import com.ssk.haoke.cloud.server.house.api.query.IHouseResourcesQueryApi;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@RocketMQMessageListener(
        topic = "haoke-resources-syns-topic",
        consumerGroup = "haoke-resources-consumer",
        messageModel = MessageModel.BROADCASTING,
        selectorExpression = "DATA_SYNS"
)
public class SearchService implements RocketMQListener<String>{

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private IHouseResourcesQueryApi houseResourcesQueryApi;

    public static final Integer ROWS = 10;
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    public static final Logger logger = LoggerFactory.getLogger(SearchService.class);
    public SearchRespDto search(String keyWord, Integer page) {
        //设置分页参数
        PageRequest pageRequest = PageRequest.of(page - 1, ROWS);
        //构造查询条件
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.multiMatchQuery(keyWord, "title", "title.pinyin"
                ).operator(Operator.AND)) // match查询
                .withPageable(pageRequest)
                .withHighlightFields(new HighlightBuilder.Field("title")) // 设置高亮
                .build();
        //使用Spring集成的ElasticsearchTemplate
        AggregatedPage<HouseData> housePage =
                this.elasticsearchTemplate.queryForPage(searchQuery,
                        HouseData.class,
                        //匿名内部类
                        new SearchResultMapper() {
                            @Override//映射搜索结果到自定义的HouseData搜索结果对象
                            public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
                                //搜索命令为0，直接返回空集合
                                if (response.getHits().totalHits == 0) {
                                    return new AggregatedPageImpl<>(Collections.emptyList(), pageable, 0L);
                                }
                                List<T> list = new ArrayList<>();
                                //泛型 T 指房源结果类 HouseData.class
                                for (SearchHit searchHit : response.getHits().getHits()) {
                                    T obj = (T) ReflectUtils.newInstance(clazz);

                                    try {
                                        //写入id
                                        FieldUtils.writeField(obj, "id", searchHit.getId(), true);
                                    } catch (IllegalAccessException e) {
                                        e.printStackTrace();
                                    }

                                    // 非高亮字段的数据写入
                                    for (Map.Entry<String, Object> entry : searchHit.getSourceAsMap().entrySet()) {
                                        //获取对应字段
                                        Field field = FieldUtils.getField(clazz, entry.getKey(), true);
                                        if (null == field) {
                                            continue;
                                        }

                                        try {
                                            //字段写入值
                                            FieldUtils.writeField(obj, entry.getKey(), entry.getValue(), true);
                                        } catch (IllegalAccessException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    //高亮字段写入
                                    for (Map.Entry<String, HighlightField> entry : searchHit.getHighlightFields().entrySet()) {
                                        //title字段，为text类型
                                        StringBuilder sb = new StringBuilder();
                                        Text[] fragments = entry.getValue().getFragments();
                                        for (Text fragment : fragments) {
                                            sb.append(fragment.toString());
                                        }
                                        // 写入高亮的内容
                                        try {
                                            FieldUtils.writeField(obj, entry.getKey(), sb.toString(), true);
                                        } catch (IllegalAccessException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    list.add(obj);
                                }
                                return new AggregatedPageImpl<>(list, pageable, response.getHits().totalHits);
                            }
                        });

        return new SearchRespDto(housePage.getTotalPages(), housePage.getContent(), null,null);

    }

    public void insertData(){

//        List<HouseResourcesRespDto> records = houseResourcesQueryApi.queryHouseResourcesList(null, 1, 200).getData().getRecords();
//        for (HouseResourcesRespDto record : records) {
//            HouseData houseData = new HouseData();
//            houseData.setId(String.valueOf(record.getId()));
//            houseData.setAddress(record.getAddress());

            IndexRequest indexRequest = new IndexRequest();
            indexRequest.source("address","地址");
            UpdateQuery updateQuery = new UpdateQueryBuilder()
                .withId("0pwEuXABuZe7lB10LVSB")
                .withClass(HouseData.class)
                .withIndexRequest(indexRequest).build();
        this.elasticsearchTemplate.update(updateQuery);
//        }

    }

    @Override
    public void onMessage(String msg) {
        System.out.println("接收到的同步房源消息->"+msg);
        HouseData houseData = null;
        try {
            if (StringUtils.isNotBlank(msg)){
                houseData = OBJECT_MAPPER.readValue(msg, HouseData.class);
            }
        } catch (IOException e) {
            logger.error("String转换houseDataVo异常");
            e.printStackTrace();
        }
        IndexQuery add = new IndexQueryBuilder().withObject(houseData).build();
        String index = this.elasticsearchTemplate.index(add);
        System.out.println(index);
    }


        public static void main(String[] args) throws MQClientException {
            DefaultMQProducer producer = new DefaultMQProducer("haoke-resources-producer");
            producer.setNamesrvAddr("192.168.19.131:9876");
            producer.start();

            producer.createTopic("broker_haoke", "haoke-resources-syns-topic", 8);
            System.out.println("创建topic成功");
            producer.shutdown();
        }

}
