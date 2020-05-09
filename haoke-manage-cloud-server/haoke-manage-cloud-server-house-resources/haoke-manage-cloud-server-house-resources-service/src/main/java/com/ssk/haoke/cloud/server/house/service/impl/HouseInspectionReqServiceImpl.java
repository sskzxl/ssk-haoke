package com.ssk.haoke.cloud.server.house.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ssk.haoke.cloud.server.common.BookingStatus;
import com.ssk.haoke.cloud.server.common.HostBookingStatus;
import com.ssk.haoke.cloud.server.house.api.dto.request.HouseBookingReqDto;
import com.ssk.haoke.cloud.server.house.api.dto.request.HouseInspectionReqReqDto;
import com.ssk.haoke.cloud.server.house.api.dto.response.HouseBookingRespDto;
import com.ssk.haoke.cloud.server.house.api.dto.response.HouseInspectionRespDto;
import com.ssk.haoke.cloud.server.house.api.dto.response.HouseResourcesRespDto;
import com.ssk.haoke.cloud.server.house.eo.HouseInspectionReqEo;
import com.ssk.haoke.cloud.server.house.eo.PageInfo;
import com.ssk.haoke.cloud.server.house.mapper.HouseEstateMapper;
import com.ssk.haoke.cloud.server.house.mapper.HouseInspectionReqMapper;
import com.ssk.haoke.cloud.server.house.rest.RestResponse;
import com.ssk.haoke.cloud.server.house.service.BaseServiceImpl;
import com.ssk.haoke.cloud.server.house.service.HouseResourcesService;
import com.ssk.haoke.cloud.server.house.service.IHouseInspectionReqService;
import com.ssk.haoke.cloud.server.user.api.dto.response.UserRespDto;
import com.ssk.haoke.cloud.server.user.api.query.IUserQueryApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* 看房请求表服务实现类
*
* @author 代码生成器
*/
@Service
public class HouseInspectionReqServiceImpl extends BaseServiceImpl<HouseInspectionReqEo>
        implements IHouseInspectionReqService {

    public static final Logger logger = LoggerFactory.getLogger(HouseInspectionReqServiceImpl.class);
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    @Resource
    private HouseResourcesService houseResourcesService;
    @Resource
    private HouseEstateMapper houseEstateMapper;
    @Resource
    private IUserQueryApi userQueryApi;
    @Resource
    private HouseInspectionReqMapper inspectionReqMapper;

    @Override
    public Long addHouseInspectionReq(HouseInspectionReqReqDto addReqDto) {
        HouseInspectionReqEo houseInspectionReqEo = new HouseInspectionReqEo();
        if (null == addReqDto){
            logger.error("新增看房请求参数不得为空");
            throw new RuntimeException("新增看房请求参数不得为空");
        }
        BeanUtils.copyProperties(addReqDto,houseInspectionReqEo,HouseInspectionReqEo.class);
        Integer save = super.save(houseInspectionReqEo);
        return save.longValue();
    }

    @Override
    public void modifyHouseInspectionReq(HouseInspectionReqReqDto modifyReqDto) {
        if (null == modifyReqDto){
            logger.error("新增看房请求参数不得为空");
            throw new RuntimeException("新增看房请求参数不得为空");
        }
        HouseInspectionReqEo houseInspectionReqEo = new HouseInspectionReqEo();
        BeanUtils.copyProperties(modifyReqDto,houseInspectionReqEo,HouseInspectionReqEo.class);
        super.update(houseInspectionReqEo);
    }

    @Override
    public void removeHouseInspectionReq(String ids) {
        String[] split = ids.split(",");
        List idList = CollectionUtils.arrayToList(split);
        super.deleteByIds(idList);
    }

    @Override
    public HouseInspectionRespDto getById(Long id) {
        HouseInspectionReqEo houseInspectionReqEo = super.queryById(id);
        if (null == houseInspectionReqEo){
            return new HouseInspectionRespDto();
        }
        HouseInspectionRespDto houseInspectionRespDto = new HouseInspectionRespDto();
        BeanUtils.copyProperties(houseInspectionReqEo,houseInspectionRespDto);
        return houseInspectionRespDto;
    }

    @Override
    public List<Map<String,String>> queryByPage(String filter, Integer pageNum, Integer pageSize) {
        HouseInspectionReqEo houseInspectionReqEo = null;
        try {
            houseInspectionReqEo = OBJECT_MAPPER.readValue(filter, HouseInspectionReqEo.class);
        } catch (IOException e) {
            logger.error("看房请求filter转换失败");
            e.printStackTrace();
        }
        if (null != houseInspectionReqEo){
            List<Map<String,String>> note = Lists.newArrayList();
            Long tenantId = houseInspectionReqEo.getTenantId();
            Long hostId = houseInspectionReqEo.getHostId();
//        房东
            if (null != hostId && null == tenantId){
                note = this.getHostNote(hostId);
            }
            if (null == hostId && null != tenantId){
                note = this.getTenantNote(tenantId);
            }
            return note;
        }
       return Lists.newArrayList();
    }

    @Override
    public HouseResourcesRespDto getHouseBooking(Long id) {
        if (null == id)return null;
        HouseResourcesRespDto houseResourcesRespDto = houseResourcesService.queryHouseResourcesById(id);

        List picList = houseResourcesRespDto.getPicList();
        if (!CollectionUtils.isEmpty(picList)){
            houseResourcesRespDto.setPic(picList.get(0).toString());
        }
        return houseResourcesRespDto;
    }
    //通过预约房源表id 查询预约详情
    public HouseBookingRespDto getHouseBookingByReqId(Long id) {
        if (null == id)return null;
        HouseInspectionReqEo eo = super.queryById(id);
        //获取租户电话
        UserRespDto data = userQueryApi.queryById(eo.getTenantId()).getData();
        Long houseResourcesId = eo.getHouseResourcesId();
        HouseResourcesRespDto houseResourcesRespDto = houseResourcesService.queryHouseResourcesById(houseResourcesId);
        List picList = houseResourcesRespDto.getPicList();
        if (!CollectionUtils.isEmpty(picList)){
            houseResourcesRespDto.setPic(picList.get(0).toString());
        }
        HouseBookingRespDto houseBookingRespDto = new HouseBookingRespDto();
        houseBookingRespDto.setPhone(data.getPhone());
        houseBookingRespDto.setHouseData(houseResourcesRespDto);
        houseBookingRespDto.setReqStatus(eo.getReqStatus());
        return houseBookingRespDto;
    }
    private PageInfo IPage2PageInfo(IPage eoIPage){
        List<HouseInspectionReqEo> eos = eoIPage.getRecords();
        List<HouseInspectionRespDto>  respDtos = Lists.newArrayList();
        for (HouseInspectionReqEo eo : eos) {
            HouseInspectionRespDto respDto = new HouseInspectionRespDto();
            BeanUtils.copyProperties(eo,respDto);
            //根据id查询用户填补租客信息
            UserRespDto userRespDto = userQueryApi.queryById(eo.getTenantId()).getData();
            respDto.setTenantName( userRespDto.getUsername());
            respDto.setTenantPhone(userRespDto.getPhone());
            //根据id查询房源填补地址信息
            HouseResourcesRespDto houseResourcesRespDto = houseResourcesService.queryHouseResourcesById(eo.getHouseResourcesId());
            respDto.setAddress(houseResourcesRespDto.getAddress());

            respDtos.add(respDto);
        }
        PageInfo<HouseInspectionRespDto> respDtoPage = new PageInfo<>();
        respDtoPage.setRecords(respDtos);
        respDtoPage.setPageNum(Integer.parseInt(String.valueOf(eoIPage.getCurrent())));
        respDtoPage.setPageSize(Integer.parseInt(String.valueOf(eoIPage.getSize())));
        Long total = eoIPage.getTotal();
        respDtoPage.setTotal(total.intValue());
        return respDtoPage;
    }

    @Override
    public RestResponse commitBooking(HouseBookingReqDto houseBookingReqDto) {
        //获取房源信息
        Long houseResourcesId = houseBookingReqDto.getHouseResourcesId();
        HouseResourcesRespDto houseResourcesRespDto = houseResourcesService.queryHouseResourcesById(houseResourcesId);
        //校验是否预约过
        HouseInspectionReqEo queryEo = new HouseInspectionReqEo();
        queryEo.setHostId(houseResourcesRespDto.getContactId());//房东id在房源信息中取
        queryEo.setTenantId(houseBookingReqDto.getUserId());//租客id
        queryEo.setReqStatus(BookingStatus.UNCONFIRMED.getStatus());
        List<HouseInspectionReqEo> listByWhere = super.queryListByWhere(queryEo);
        if (!CollectionUtils.isEmpty(listByWhere)){
            //已预约过
            return new RestResponse(10000,"您的预约以收到，请勿重复预约");
        }
        HouseInspectionReqEo houseInspectionReqEo = new HouseInspectionReqEo();
        BeanUtils.copyProperties(houseBookingReqDto,houseInspectionReqEo,"userId");
        houseInspectionReqEo.setTenantId(houseBookingReqDto.getUserId());
        houseInspectionReqEo.setReqStatus(BookingStatus.UNCONFIRMED.getStatus());//刚提交预约看房，状态为待确认
        houseInspectionReqEo.setReqTime(new Date());
        Date startTime = houseBookingReqDto.getStartTime();
        Date endTime = houseBookingReqDto.getEndTime();
        if (null != startTime && null != endTime){
            Instant startInstant = startTime.toInstant();
            Instant endInstant = endTime.toInstant();
            ZoneId zoneId = ZoneId.systemDefault();
            houseInspectionReqEo.setStartTime(startInstant.atZone(zoneId).toLocalDateTime());
            houseInspectionReqEo.setEndTime(endInstant.atZone(zoneId).toLocalDateTime());
        }

        houseInspectionReqEo.setHostId(houseResourcesRespDto.getContactId());
        Integer save = super.save(houseInspectionReqEo);
        return RestResponse.SUCCEED;
    }


    public List<Map<String,String>> getHostNote(Long id) {
        //王XX 预约 2018-5-14  10:00-12：00 看 回龙观东大街龙腾四区3号楼1单元202室
        List<Map<String,String>> mapList = Lists.newArrayList();
        QueryWrapper<HouseInspectionReqEo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("host_id",id);
        List<HouseInspectionReqEo> houseInspectionReqEos = inspectionReqMapper.selectList(queryWrapper);
        for (HouseInspectionReqEo eo : houseInspectionReqEos) {
            Map<String,String> noteMap = Maps.newHashMap();
            String address = getAddressById(eo.getHouseResourcesId());
            UserRespDto userById = getUserById(eo.getTenantId());
            StringBuilder respStr = new StringBuilder();
            //您 预约 2018-5-14  10:00-12：00 看 回龙观东大街龙腾四区3号楼1单元202室
            respStr.append(userById.getUsername());
            respStr.append("预约");
            respStr.append(eo.getStartTime()+"-");
            LocalDateTime endTime = eo.getEndTime();
            respStr.append(endTime.getHour()+"-"+endTime.getMinute());
            respStr.append("看");
            respStr.append(address);
            //获取通知信息
//            respStr.append(HostBookingStatus.getNoticeByStatus(eo.getReqStatus()).getNotice());
            noteMap.put("id",eo.getId().toString());
            noteMap.put("msg",respStr.toString());
            noteMap.put("status",eo.getReqStatus().toString());
            mapList.add(noteMap);
        }
        return mapList;
    }



    public List<Map<String,String>> getTenantNote(Long id) {
        List<Map<String,String>> mapList = Lists.newArrayList();
        QueryWrapper<HouseInspectionReqEo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tenant_id",id);
        List<HouseInspectionReqEo> houseInspectionReqEos = inspectionReqMapper.selectList(queryWrapper);
        for (HouseInspectionReqEo eo : houseInspectionReqEos) {
            Map<String,String> noteMap = Maps.newHashMap();
            String address = getAddressById(eo.getHouseResourcesId());
            StringBuilder respStr = new StringBuilder();
            //您 预约 2018-5-14  10:00-12：00 看 回龙观东大街龙腾四区3号楼1单元202室
            respStr.append("您预约");
            respStr.append(eo.getStartTime()+"-");
            LocalDateTime endTime = eo.getEndTime();
            respStr.append(endTime);
//            respStr.append(endTime.getHour()+"-"+endTime.getMinute());
            respStr.append("看");
            respStr.append(address);
            //获取通知信息
            respStr.append(BookingStatus.getNoticeByStatus(eo.getReqStatus()).getNotice());
            noteMap.put("id",eo.getId().toString());
            noteMap.put("msg",respStr.toString());
            noteMap.put("status",eo.getReqStatus().toString());
            mapList.add(noteMap);
        }
        return mapList;
    }


    private String getAddressById(Long houseResourcesId) {
        HouseResourcesRespDto houseResourcesRespDto = houseResourcesService.queryHouseResourcesById(houseResourcesId);
        if (null != houseResourcesRespDto){
            return houseResourcesRespDto.getAddress();
        }else {
            return null;
        }
    }
    private UserRespDto getUserById(Long id) {
        UserRespDto data = userQueryApi.queryById(id).getData();
        if (null != data){
            return data;
        }else {
            return null;
        }
    }
    @Override
    public boolean updateStatus(Integer bookingStatus) {
        HouseInspectionReqEo eo = new HouseInspectionReqEo();
        eo.setReqStatus(bookingStatus);
        Integer update = super.update(eo);
        if (1 == update){
            return true;
        }else {
            return false;
        }
    }

    private String getNameById(Long tenantId) {
        UserRespDto data = userQueryApi.queryById(tenantId).getData();
        if (null  != data){
            return data.getUsername();
        }else {
            return null;
        }
    }
}
