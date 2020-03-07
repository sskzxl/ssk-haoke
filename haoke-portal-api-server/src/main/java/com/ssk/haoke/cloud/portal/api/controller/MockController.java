package com.ssk.haoke.cloud.portal.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:9000")
@RequestMapping("mock")
@Api(tags = "mock数据")
public class MockController {
    @Autowired
    private com.ssk.haoke.cloud.portal.api.config.MockConfig mockConfig;

    /**
     * 菜单
     *
     * @return
     */
    @GetMapping("index/menu")
    @ApiOperation("菜单")
    public String indexMenu() {
        return this.mockConfig.getIndexMenu();
    }

    /**
     * 首页资讯
     *
     * @return
     */
    @GetMapping("index/info")
    @ApiOperation("首页资讯")
    public String indexInfo() {
        return this.mockConfig.getIndexInfo();
    }

    /**
     * 首页问答
     *
     * @return
     */
    @GetMapping("index/faq")
    @ApiOperation("首页问答")
    public String indexFaq() {
        return this.mockConfig.getIndexFaq();
    }

    /**
     * 首页房源信息
     *
     * @return
     */
    @GetMapping("index/house")
    @ApiOperation("首页问答")
    public String indexHouse() {
        return this.mockConfig.getIndexHouse();
    }

    /**
     * 查询资讯
     *
     * @param type
     * @return
     */
    @PostMapping("infos/list")
    @ApiOperation("查询资讯")
    public String infosList(@RequestParam("type") Integer type) {
        switch (type) {
            case 1:
                return this.mockConfig.getInfosList1();
            case 2:
                return this.mockConfig.getInfosList2();
            case 3:
                return this.mockConfig.getInfosList3();
        }
        return this.mockConfig.getInfosList1();
    }

    /**
     * 我的中心
     *
     * @return
     */
    @GetMapping("my/info")
    public String myInfo() {
        return this.mockConfig.getMy();
    }

}