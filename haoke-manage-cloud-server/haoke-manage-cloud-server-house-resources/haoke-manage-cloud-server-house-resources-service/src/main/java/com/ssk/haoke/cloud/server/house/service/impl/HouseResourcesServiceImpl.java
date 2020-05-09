package com.ssk.haoke.cloud.server.house.service.impl;

import com.alibaba.nacos.client.utils.JSONUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.ssk.haoke.cloud.server.common.AttachMap;
import com.ssk.haoke.cloud.server.house.api.dto.request.HouseResourcesReqDto;
import com.ssk.haoke.cloud.server.house.api.dto.response.DropDownRespDto;
import com.ssk.haoke.cloud.server.house.api.dto.response.HouseResourcesListRespDto;
import com.ssk.haoke.cloud.server.house.api.dto.response.HouseResourcesRespDto;
import com.ssk.haoke.cloud.server.house.api.vo.search.HouseDataVo;
import com.ssk.haoke.cloud.server.house.eo.*;
import com.ssk.haoke.cloud.server.house.mapper.*;
import com.ssk.haoke.cloud.server.house.service.BaseServiceImpl;
import com.ssk.haoke.cloud.server.house.service.HouseResourcesService;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HouseResourcesServiceImpl extends BaseServiceImpl<HouseResourcesEo> implements HouseResourcesService {

    public static final Logger logger = LoggerFactory.getLogger(HouseResourcesServiceImpl.class);
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    @Resource
    private HouseEstateMapper houseEstateMapper;
    @Resource
    private RHouseAttachmentsMapper houseAttachmentsMapper;
    @Resource
    private HousePicMapper housePicMapper;
    @Resource
    private AttachmentsMapper attachmentsMapper;
//    @Autowired
//    private RocketMQTemplate rocketMQTemplate;
    @Resource
    private HouseMapService houseMapService;
    @Resource
    private HouseResourcesMapper houseResourcesMapper;

    /**
     * @param id
     * @return
     */
    public boolean deleteHouseResources(Long id) {
        return super.deleteById(id) > 0 ? true : false;
    }


    /**
     * @param houseResourcesReqDto
     * @return -1:输入的参数不符合要求，0：数据插入数据库失败，1：成功
     */
    public Long saveHouseResources( HouseResourcesReqDto houseResourcesReqDto) {
        HouseResourcesEo houseResourcesEo = new HouseResourcesEo();
        BeanUtils.copyProperties(houseResourcesReqDto, houseResourcesEo, "attachments","pic");
        //添加时还没有通过审核
        houseResourcesEo.setByReview(0);
        Integer save = super.save(houseResourcesEo);
        if (save == 1){
            //图片新增
            List<String> pics = houseResourcesReqDto.getPic();
            addPics(pics,houseResourcesEo.getId());
            //配套设施新增
            Integer[] facilities = houseResourcesReqDto.getAttachments();
            addFacilities(facilities,houseResourcesEo.getId());
        }
        return houseResourcesEo.getId();
    }
    public void synsHouseData(List<Long> ids){
        QueryWrapper<HouseResourcesEo> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id",ids);
        queryWrapper.eq("by_review",0);//未通过审核的
        List<HouseResourcesEo> houseResourcesEos = houseResourcesMapper.selectList(queryWrapper);
        for (HouseResourcesEo houseResourcesEo : houseResourcesEos) {
            //发送MQ消息给es和mongodb同步数据
            HouseDataVo houseDataVo = new HouseDataVo();
            BeanUtils.copyProperties(houseResourcesEo, houseDataVo);
            String houseData = null;
            if (null != houseDataVo){
                try {
                    houseData = OBJECT_MAPPER.writeValueAsString(houseDataVo);
                } catch (JsonProcessingException e) {
                    logger.error("houseDataVo转换String异常");
                    e.printStackTrace();
                }
                Message message = MessageBuilder.withPayload(houseData).build();
//                rocketMQTemplate.send("haoke-resources-syns-topic:DATA_SYNS",message);
                houseMapService.addHouseData(houseResourcesEo.getAddress(),houseResourcesEo.getId(),houseResourcesEo.getTitle());
            }
            houseResourcesEo.setByReview(1);
            houseResourcesMapper.updateById(houseResourcesEo);
        }

    }

    private void addPics(List<String> pics , Long id){
        if (!CollectionUtils.isEmpty(pics)){
            for (String pic : pics) {
                HousePicEo housePicEo = new HousePicEo();
                housePicEo.setPath(pic);
                housePicEo.setHouseResourcesId(id);
                housePicEo.setUpdated(LocalDateTime.now());
                housePicEo.setCreated(LocalDateTime.now());
                housePicMapper.insert(housePicEo);
            }
        }
    }
    private void addFacilities(Integer[] facilities,Long id){
        if (null != facilities && facilities.length > 0)
            for (Integer facility : facilities) {
                RHouseAttachmentsEo rHouseAttachmentsEo = new RHouseAttachmentsEo();
                rHouseAttachmentsEo.setHouseResourcesId(id);
                rHouseAttachmentsEo.setAttachmentsId(facility.longValue());
                houseAttachmentsMapper.insert(rHouseAttachmentsEo);
            }
    }

    /**
     * 查询房源列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<HouseResourcesListRespDto> queryHouseResourcesList(String filter, Integer pageNum, Integer pageSize) {
        HouseResourcesReqDto houseResourcesReqDto = new HouseResourcesReqDto();
        try {
            //根据filter条件的json字符串转换为请求dto对象
            houseResourcesReqDto = (HouseResourcesReqDto) JSONUtils.deserializeObject(filter, HouseResourcesReqDto.class);
        } catch (IOException e) {
            logger.error("string转对象异常：{}", e);
        }
        //提取查询条件，转换为wrapper
        QueryWrapper<HouseResourcesEo> queryWrapper = getHouseResourcesWrapper(houseResourcesReqDto);
        //查询结果
        IPage<HouseResourcesEo> eoIPage = super.queryPageList(queryWrapper, pageNum, pageSize);
        //转换为结果对象
        PageInfo<HouseResourcesListRespDto> pageInfo = IPage2PageInfo(eoIPage);
        //图片处理
        List<HouseResourcesListRespDto> records = pageInfo.getRecords();
        if (!CollectionUtils.isEmpty(records)){
            for (HouseResourcesListRespDto record : records) {
                QueryWrapper<HousePicEo> picQueryWrapper = new QueryWrapper<>();
                picQueryWrapper.eq("house_resources_id",record.getId());
                List<HousePicEo> housePicEos = housePicMapper.selectList(picQueryWrapper);
                //列表显示第一张
                if (!CollectionUtils.isEmpty(housePicEos)){
                    record.setPic(housePicEos.get(0).getPath());
                }
            }
        }
        return pageInfo;
    }

    private QueryWrapper<HouseResourcesEo> getHouseResourcesWrapper(HouseResourcesReqDto houseResourcesReqDto) {
        if (null == houseResourcesReqDto )return new QueryWrapper<>();
        HouseResourcesEo houseResourcesEo = new HouseResourcesEo();
        BeanUtils.copyProperties(houseResourcesReqDto,houseResourcesEo,"rentMethod","rent","address","title");
        if (null != houseResourcesReqDto.getRentMethod() && 0 == houseResourcesReqDto.getRentMethod()) {
            houseResourcesEo.setRentMethod(null);
        }else {
            houseResourcesEo.setRentMethod(houseResourcesReqDto.getRentMethod());
        }
        QueryWrapper<HouseResourcesEo> queryWrapper = new QueryWrapper<>(houseResourcesEo);
        if (StringUtils.isNotEmpty(houseResourcesReqDto.getLowPrice())) {
            queryWrapper.ge("rent", houseResourcesReqDto.getLowPrice());
        }
        if (StringUtils.isNotEmpty(houseResourcesReqDto.getUpPrice())) {
            queryWrapper.le("rent", houseResourcesReqDto.getUpPrice());
        }
        java.lang.String address = houseResourcesReqDto.getAddress();
        if (StringUtils.isNotEmpty(address)) {
            if (address.endsWith("区")){
                address = address.substring(0,address.length()-1);
                queryWrapper.like("address", address);
            }
        }
        @NotNull String title = houseResourcesReqDto.getTitle();
        if (StringUtils.isNotEmpty(title)) {
            queryWrapper.like("title", title);

        }

        return queryWrapper;
    }

    @Override
    public List<DropDownRespDto> getAllCity() {
        List<EstateEo> estateEos = houseEstateMapper.selectList(new QueryWrapper<>());
        List<DropDownRespDto> results = Lists.newArrayList();
        List<String> citys = Lists.newArrayList();
        Map<String, List<EstateEo>> collect = estateEos.stream().collect(Collectors.groupingBy(e -> e.getCity()));
        for (List<EstateEo> eos : collect.values()) {
            //城市
            DropDownRespDto dropDownRespDto = new DropDownRespDto();
            List<Long> ids = eos.stream().map(estateEo -> estateEo.getId()).collect(Collectors.toList());
            dropDownRespDto.setValue(ids);
            dropDownRespDto.setLabel(eos.get(0).getCity());
            //地区
            Map<String, List<EstateEo>> area = eos.stream().collect(Collectors.groupingBy(e -> e.getArea()));
            List<DropDownRespDto> areaResult = Lists.newArrayList();
            for (List<EstateEo> areaList : area.values()) {
                DropDownRespDto childRespDto = new DropDownRespDto();
                childRespDto.setLabel(areaList.get(0).getArea());
                List<Long> areaIds = areaList.stream().map(estateEo -> estateEo.getId()).collect(Collectors.toList());
                childRespDto.setValue(areaIds);
                areaResult.add(childRespDto);
            }
            dropDownRespDto.setChild(areaResult);
            results.add(dropDownRespDto);
        }
        return results;
    }

    /**
     * 根据id查询房源
     *
     * @param id
     * @return
     */
    public HouseResourcesRespDto queryHouseResourcesById(Long id) {
        HouseResourcesEo houseResourcesEo = super.queryById(id);
        if (null == houseResourcesEo) {
            return null;
        } else {
            HouseResourcesRespDto houseResourcesRespDto = new HouseResourcesRespDto();
            BeanUtils.copyProperties(houseResourcesEo, houseResourcesRespDto, "facilities", "pic");
            //根据房源和配套设施维护表查询 配套设施id
            RHouseAttachmentsEo houseAttachmentsEo = new RHouseAttachmentsEo();
            houseAttachmentsEo.setHouseResourcesId(id);
            List<RHouseAttachmentsEo> houseAttachmentsEos = houseAttachmentsMapper.selectList(new QueryWrapper<>(houseAttachmentsEo));
            List<Long> attachmentIds = houseAttachmentsEos.stream().map(e -> e.getAttachmentsId()).collect(Collectors.toList());
            //根据id查询配套设施
            QueryWrapper<AttachmentsEo> attachmentQueryWrapper = new QueryWrapper<>();
            attachmentQueryWrapper.in("id",attachmentIds);
            List<AttachmentsEo> attachmentsEos = attachmentsMapper.selectList(attachmentQueryWrapper);
            if (!CollectionUtils.isEmpty(attachmentsEos)){
                Map<Long, String> attachMap = attachmentsEos.stream().collect(Collectors.toMap(e1 -> e1.getId(), e2 -> e2.getUrl()));
                Long index = 1L;
                while (index <= 10){
                    String path = attachMap.get(index);
                    if (StringUtils.isBlank(path)){
                        attachMap.put(index, AttachMap.attachMap.get(index));
                    }
                    index++;
                }
                houseResourcesRespDto.setFacilities(attachMap);
            }
            //图片
            QueryWrapper<HousePicEo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("house_resources_id",id);
            List<HousePicEo> housePicEos = housePicMapper.selectList(queryWrapper);
            List<String> pics = housePicEos.stream().map(e -> e.getPath()).collect(Collectors.toList());
            houseResourcesRespDto.setPicList(pics);
            return houseResourcesRespDto;
        }
    }

    @Override
    public PageInfo<HouseResourcesRespDto> getPageByCity(String cityName, Integer pageNum, Integer pageSize) {
        EstateEo estateEo = new EstateEo();
        estateEo.setCity(cityName);
        //查出所有该城市的楼盘
        List<EstateEo> estateEos = houseEstateMapper.selectList(new QueryWrapper<>(estateEo));
        //筛选所有楼盘id
        List<Long> estateIds = estateEos.stream().map(e -> e.getId()).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(estateEos)) {
            HouseResourcesEo houseResourcesEo = new HouseResourcesEo();
            QueryWrapper<HouseResourcesEo> queryWrapper = new QueryWrapper<>(houseResourcesEo);
            queryWrapper.in("estate_id", estateIds);
            IPage<HouseResourcesEo> houseResourcesEoIPage = this.queryPageList(queryWrapper, pageNum, pageSize);
//            PageInfo<HouseResourcesRespDto> dtoPageInfo = IPage2PageInfo(houseResourcesEoIPage);
//            return dtoPageInfo;
            return null;
        } else {
            return null;
        }
    }

    /**
     * 更新房源
     *
     * @param houseResourcesReqDto
     * @return
     */
    public boolean updateHouseResources(HouseResourcesReqDto houseResourcesReqDto) {
        if (null == houseResourcesReqDto) {
            return false;
        }
        HouseResourcesEo houseResourcesEo = new HouseResourcesEo();
        BeanUtils.copyProperties(houseResourcesReqDto, houseResourcesEo, HouseResourcesEo.class);
        houseResourcesEo.setByReview(0);//修改完需要重新审核
        Integer update = super.update(houseResourcesEo);
        return update == 1;
    }

    private PageInfo<HouseResourcesListRespDto> IPage2PageInfo(IPage eoIPage) {
        List<HouseResourcesEo> eos = eoIPage.getRecords();
        if (CollectionUtils.isEmpty(eos)) {
            return new PageInfo<>();
        }
        List<HouseResourcesListRespDto> respDtos = Lists.newArrayList();
        for (HouseResourcesEo eo : eos) {
            HouseResourcesListRespDto respDto = new HouseResourcesListRespDto();
            BeanUtils.copyProperties(eo, respDto);
            respDtos.add(respDto);
        }
        PageInfo<HouseResourcesListRespDto> respDtoPage = new PageInfo<HouseResourcesListRespDto>();
        respDtoPage.setRecords(respDtos);
        respDtoPage.setPageNum(Integer.parseInt(String.valueOf(eoIPage.getCurrent())));
        respDtoPage.setPageSize(Integer.parseInt(String.valueOf(eoIPage.getSize())));
        Long total = eoIPage.getTotal();
        respDtoPage.setTotal(total.intValue());
        return respDtoPage;
    }

}