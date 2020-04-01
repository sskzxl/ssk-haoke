package com.ssk.haoke.cloud.portal.api.controller;

import com.google.common.collect.Sets;
import com.ssk.haoke.cloud.portal.api.service.impl.SearchService;
import com.ssk.haoke.cloud.portal.api.vo.SearchRespDto;
import com.ssk.haoke.cloud.server.house.rest.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("search")
@Api(tags = "房源搜索服务")
public class SearchController {
    @Autowired
    private SearchService searchService;
    @Autowired
    private RedisTemplate redisTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchController.class);

    @GetMapping
    @ApiOperation(value = "房源搜索")
    public RestResponse<SearchRespDto> search(@RequestParam("keyword") String keyword,
                                              @RequestParam(value = "page", defaultValue = "1") Integer page){
        if(page > 100){//防止爬虫爬取过多的数据
            page = 1;
        }
        SearchRespDto search = this.searchService.search(keyword, page);

        Set<String> hotWordSet = getHodWord(keyword,search);
        Set<String> recommendWordSet = getRecommendWord(keyword,search);
        search.setHotWord(hotWordSet);
        search.setRecommendWord(recommendWordSet);
        return new RestResponse<>(search);

    }

    private Set<String> getRecommendWord(String keyword,SearchRespDto search) {
        String recommendRedisKey = "KEY_RECOMMEND_WORD";
        if (search.getTotalPage()>0){//统计 搜索结果大于0的关键词 的次数
            redisTemplate.opsForZSet().incrementScore(recommendRedisKey,keyword,1);
        }
        //根据次数排行前五返回
        Set set = redisTemplate.opsForZSet().reverseRange(recommendRedisKey, 0, 4);
        return set;
    }

    private Set<String> getHodWord(String keyword , SearchRespDto search) {
        //小于一页时显示热词
        String redisKey = "KEY_HOT_WORD";
        Set<String> set = Sets.newHashSet();
        if(search.getTotalPage() <= 1){
            //需要查询热词,按照得分倒序排序，获取前5条数据
            set = this.redisTemplate.opsForZSet().reverseRange(redisKey,0,4);
            search.setHotWord(set);
        }
        //处理热词
        Integer count = ((Math.max(search.getTotalPage(),1) - 1) * SearchService.ROWS ) + search.getList().size();
        //采用zset方式进行储存，值所对应的得分是数据条数。数据条数大于0再返回
        if (count > 10){
            this.redisTemplate.opsForZSet().add(redisKey,keyword,count);
        }
        //记录日志
        LOGGER.info("[Search]搜索关键字为：" + keyword + "，结果数量为：" + count);
        return set;
    }

    @GetMapping("insert")
    public void insertDate(){
        searchService.insertData();
    }
}
