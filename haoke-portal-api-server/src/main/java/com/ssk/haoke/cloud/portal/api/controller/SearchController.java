package com.ssk.haoke.cloud.portal.api.controller;

import com.ssk.haoke.cloud.portal.api.service.impl.SearchService;
import com.ssk.haoke.cloud.portal.api.vo.SearchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("search")
public class SearchController {
    @Autowired
    private SearchService searchService;
    @Autowired
    private RedisTemplate redisTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchController.class);

    @GetMapping
    public SearchResult search(@RequestParam("keyword") String keyword,
                               @RequestParam(value = "page", defaultValue = "1") Integer page){
        if(page > 100){//防止爬虫爬取过多的数据
            page = 1;
        }
        SearchResult search = this.searchService.search(keyword, page);

        String redisKey = "KEY_HOT_WORD";

        //小于一页时显示热词
        if(search.getTotalPage() <= 1){
            //需要查询热词,按照得分倒序排序，获取前5条数据
            Set<String> set = this.redisTemplate.opsForZSet().reverseRange(redisKey,0,4);
            search.setHotWord(set);
        }
        //处理热词
        Integer count = ((Math.max(search.getTotalPage(),1) - 1) * SearchService.ROWS ) + search.getList().size();

        //采用zset方式进行储存，值所对应的得分是数据条数
        this.redisTemplate.opsForZSet().add(redisKey,keyword,count);

        //记录日志
        LOGGER.info("[Search]搜索关键字为：" + keyword + "，结果数量为：" + count);
        return search;

    }
}
