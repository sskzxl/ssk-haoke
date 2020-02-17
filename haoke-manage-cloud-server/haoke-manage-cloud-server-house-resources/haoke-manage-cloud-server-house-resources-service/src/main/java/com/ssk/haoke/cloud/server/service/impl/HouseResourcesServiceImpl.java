package com.ssk.haoke.cloud.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ssk.haoke.cloud.server.pojo.HouseResources;
import com.ssk.haoke.cloud.server.service.HouseResourcesService;
import com.ssk.haoke.cloud.server.vo.PageInfo;
import com.ssk.haoke.cloud.server.vo.Pagination;
import com.ssk.haoke.cloud.server.vo.TableResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class HouseResourcesServiceImpl extends BaseServiceImpl<HouseResources>
        implements HouseResourcesService {
    /**
     *
     * @param id
     * @return
     */
    public boolean deleteHouseResources(Long id){
        return super.deleteById(id)>0 ? true : false;
    }


    /**
     * @param houseResources
     * @return -1:输入的参数不符合要求，0：数据插入数据库失败，1：成功
     */
    public int saveHouseResources(HouseResources houseResources) {
        // 编写校验逻辑，如果教研不通过，返回-1
        if (StringUtils.isBlank(houseResources.getTitle())) {
            return -1;
        }
        // 其他校验以及逻辑省略 ……
        return super.save(houseResources);
    }

    /**
     * 查询房源列表
     *
     * @param page
     * @param pageSize
     * @return
     */
    public PageInfo<HouseResources> queryHouseResourcesList(Integer page, Integer pageSize) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("updated");
        IPage iPage = super.queryPageList(queryWrapper, page, pageSize);
        long total = iPage.getTotal();
        int totalInt = Integer.parseInt(String.valueOf(total));
        return new PageInfo<>(page,pageSize,totalInt,iPage.getRecords());
    }

    /**
     * 根据id查询房源
     *
     * @param id
     * @return
     */
    public HouseResources queryHouseResourcesById(Long id) {
        return super.queryById(id);
    }

    /**
     * 更新房源
     * @param houseResources
     * @return
     */
    public boolean updateHouseResources(HouseResources houseResources) {
        Integer update = super.update(houseResources);
        return update == 1;
    }



}