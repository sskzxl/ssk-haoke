package com.ssk.haoke.cloud.server.user.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.ssk.haoke.cloud.server.user.api.dto.request.UserReqDto;
import com.ssk.haoke.cloud.server.user.api.dto.response.UserRespDto;
import com.ssk.haoke.cloud.server.house.service.BaseServiceImpl;
import com.ssk.haoke.cloud.server.user.service.IUserService;
import com.ssk.haoke.cloud.server.house.eo.PageInfo;
import com.ssk.haoke.cloud.server.user.eo.UserEo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;

/**
* 服务实现类
*
* @author 代码生成器
*/
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<UserEo> implements IUserService {
    public static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    @Override
    public Long addUser(UserReqDto addReqDto) {
        UserEo userEo = new UserEo();
        if (null == addReqDto){
            logger.error("用户新增参数不得为空");
            throw new RuntimeException("用户新增参数不得为空");
        }
        BeanUtils.copyProperties(addReqDto,userEo,UserEo.class);
        Integer save = super.save(userEo);
        return save.longValue();
    }

    @Override
    public void modifyUser(UserReqDto modifyReqDto) {
        if (null == modifyReqDto){
            logger.error("用户新增参数不得为空");
            throw new RuntimeException("用户新增参数不得为空");
        }
        UserEo userEo = new UserEo();
        BeanUtils.copyProperties(modifyReqDto,userEo,UserEo.class);
        super.update(userEo);
    }

    @Override
    public void removeUser(String ids) {
        String[] split = ids.split(",");
        List idList = CollectionUtils.arrayToList(split);
        super.deleteByIds(idList);
    }

    @Override
    public UserRespDto getById(Long id) {
        UserEo userEo = super.queryById(id);
        if (null == userEo){
            return new UserRespDto();
        }
        UserRespDto userRespDto = new UserRespDto();
        BeanUtils.copyProperties(userEo,userRespDto);
        return userRespDto;
    }

    @Override
    public PageInfo<UserRespDto> queryByPage(String filter, Integer pageNum, Integer pageSize) {
        UserEo userEo = new UserEo();
        try {
            userEo = OBJECT_MAPPER.readValue(filter, UserEo.class);
        } catch (IOException e) {
            logger.error("string转对象异常");
        }
        IPage<UserEo> eoIPage = queryPageListByWhere(userEo, pageNum, pageSize);
        PageInfo respDtoPage = IPage2PageInfo(eoIPage);
        return respDtoPage;
    }

    private PageInfo IPage2PageInfo(IPage eoIPage){
        List<UserEo> eos = eoIPage.getRecords();
        List<UserRespDto>  respDtos = Lists.newArrayList();
        for (UserEo eo : eos) {
            UserRespDto respDto = new UserRespDto();
            BeanUtils.copyProperties(eo,respDto);
            respDtos.add(respDto);
        }
        PageInfo<UserRespDto> respDtoPage = new PageInfo<>();
        respDtoPage.setRecords(respDtos);
        respDtoPage.setPageNum(Integer.parseInt(String.valueOf(eoIPage.getCurrent())));
        respDtoPage.setPageSize(Integer.parseInt(String.valueOf(eoIPage.getSize())));
        Long total = eoIPage.getTotal();
        respDtoPage.setTotal(total.intValue());
        return respDtoPage;
    }
}
