package com.ssk.haoke.cloud.api.controller;

import com.ssk.haoke.cloud.api.service.IHouseResourcesService;
import com.ssk.haoke.cloud.server.pojo.HouseResources;
import com.ssk.haoke.cloud.server.vo.PageInfo;
import com.ssk.haoke.cloud.server.vo.TableResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("house/resources")
@CrossOrigin
@Api(tags = "好客租房-管理应用",description = "房源管理")
public class HouseResourcesController {
    @Autowired
    public static final Logger LOGGER = LoggerFactory.getLogger(HouseResourcesController.class);
    @Autowired
    private IHouseResourcesService houseResourcesService;

    /**
     * 新增房源
     *
     * @param houseResources json数据
     * @return
     */
    @PostMapping
    @ResponseBody
    @ApiOperation(value = "新增房源")
    public ResponseEntity<Void> save(@RequestBody HouseResources houseResources) {
        try {
            System.out.println(houseResources);
            boolean bool = this.houseResourcesService.save(houseResources);
            if (bool) {
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    /**
     * 查询房源列表
     *
     * @param houseResources
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping
    @ApiOperation(value = "查询房源列表")
    public ResponseEntity<TableResult<HouseResources>> list(HouseResources houseResources,
                                            @RequestParam(name = "currentPage",
                                                    defaultValue = "1") Integer currentPage,
                                            @RequestParam(name = "pageSize",
                                                    defaultValue = "10") Integer pageSize) {
        return ResponseEntity.ok(this.houseResourcesService.queryList(currentPage,pageSize,houseResources));
    }
    /**
     * 修改房源
     *
     * @param houseResources json数据
     * @return
     */
    @PutMapping
    @ResponseBody
    @ApiOperation(value = "更新房源")
    public ResponseEntity<Void> update(@RequestBody HouseResources houseResources) {
        try {
            boolean bool = this.houseResourcesService.update(houseResources);
            System.out.println("进入更新房源");
            if (bool) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }else {
                return ResponseEntity.ok().build();
            }
        } catch (Exception e) {
            LOGGER.error("更新房源异常:{}",e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @DeleteMapping("{id}")
    @ResponseBody
    @ApiOperation(value = "根据id删除房源")
    public ResponseEntity delete(@PathVariable("id") Long id){
        System.out.println("进来了吗");
        try {
            if(this.houseResourcesService.delete(id)){
                return ResponseEntity.ok().build();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    @GetMapping("{id}")
    @ResponseBody
    @ApiOperation(value = "根据id获取房源")
    public ResponseEntity get(@PathVariable("id") Long id){
       return ResponseEntity.ok(houseResourcesService.queryById(id));
    }
}