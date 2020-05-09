package com.ssk.haoke.cloud.server.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ssk.haoke.cloud.server.common.CommonConstant;
import com.ssk.haoke.cloud.server.common.ServiceResponse;
import com.ssk.haoke.cloud.server.common.UserConst;
import com.ssk.haoke.cloud.server.house.eo.PageInfo;
import com.ssk.haoke.cloud.server.house.service.BaseServiceImpl;
import com.ssk.haoke.cloud.server.user.api.dto.request.UserReqDto;
import com.ssk.haoke.cloud.server.user.api.dto.response.UserRespDto;
import com.ssk.haoke.cloud.server.user.eo.UserEo;
import com.ssk.haoke.cloud.server.user.mapper.UserMapper;
import com.ssk.haoke.cloud.server.user.service.IUserService;
import com.ssk.haoke.cloud.server.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
* 服务实现类
*
* @author 代码生成器
*/
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<UserEo> implements IUserService {
    public static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    @Autowired(required = false)
    private UserMapper userMapper;
    @Value("${user.default.head}")
    private String DEFAULT_HEAD;
    @Override
    public Long addUser(UserReqDto addReqDto) {
        UserEo userEo = new UserEo();
        if (null == addReqDto){
            logger.error("用户新增参数不得为空");
            throw new RuntimeException("用户新增参数不得为空");
        }
        BeanUtils.copyProperties(addReqDto,userEo,UserEo.class);
        String MD5Password = MD5Util.MD5EncodeUtf8(userEo.getPassword());
        userEo.setPassword(MD5Password);
        super.save(userEo);
        return userEo.getId();
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
        Map map = Maps.newHashMap();
        try {
            map = OBJECT_MAPPER.readValue(filter, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        QueryWrapper<UserEo> queryWrapper = new QueryWrapper<>(userEo);
        Object usernameObj = map.get("username");
        if (null != usernameObj){
            queryWrapper.like("username",usernameObj.toString());
        }

        queryWrapper.orderByDesc("updated");
        IPage<UserEo> eoIPage = queryPageList(queryWrapper, pageNum, pageSize);
        PageInfo respDtoPage = IPage2PageInfo(eoIPage);
        return respDtoPage;
    }

    @Override
    public ServiceResponse<UserRespDto> login(String username, String password) {
        UserEo userEo = new UserEo();
        userEo.setUsername(username);
        //查询数据库
        UserEo result = userMapper.selectOne(new QueryWrapper<>(userEo));
        if (null == result){
            //用户不存在
            return ServiceResponse.createByErrorMessage(CommonConstant.INVALID_USER);
        }
        //密码md5算法加密
        String MD5Password = MD5Util.MD5EncodeUtf8(password);
        userEo.setPassword(MD5Password);
        UserEo selectOne = userMapper.selectOne(new QueryWrapper<>(userEo));
        if (null == selectOne ){
            //密码错误
            return ServiceResponse.createByErrorMessage(CommonConstant.PASSWORD_ERROR);
        }
        UserRespDto userRespDto = new UserRespDto();
        BeanUtils.copyProperties(selectOne,userRespDto);
        userRespDto.setPassword(StringUtils.EMPTY);
        //返回用户信息和登陆状态
        return ServiceResponse.createBySuccess(CommonConstant.LOGIN_SUCCESS,userRespDto);
    }

    @Override
    public ServiceResponse register(UserReqDto userReqDto) {
        //注册时检查账号
        ServiceResponse<String> validResponse = this.checkValid(userReqDto.getUsername(), UserConst.USERNAME);
        if(validResponse.isSuccess()){//校验成功说明用户存在，
            return ServiceResponse.createByErrorMessage("用户已存在，注册失败");
        }
        //MD5加密
        userReqDto.setPassword(MD5Util.MD5EncodeUtf8(userReqDto.getPassword()));
        UserEo userEo = new UserEo();
        BeanUtils.copyProperties(userReqDto,userEo);
        if (StringUtils.isEmpty(userEo.getHeadUrl())){//用户不传图片时使用默认的
            userEo.setHeadUrl(DEFAULT_HEAD);
        }
        int insert = userMapper.insert(userEo);
        if(insert == 0){
            return ServiceResponse.createByErrorMessage("注册失败");
        }
        return ServiceResponse.createBySuccessResultMsg("注册成功");
    }

    @Override
    public ServiceResponse checkValid(String str, String type) {
        if (StringUtils.isNotEmpty(type)){
            if (UserConst.USERNAME.equals(type)){
                UserEo userEo = new UserEo();
                userEo.setUsername(str);
                if (null == userMapper.selectOne(new QueryWrapper<>(userEo))){
                    return ServiceResponse.createByErrorMessage("用户不存在");
                }
            }
        }else {
            return ServiceResponse.createByErrorMessage("参数错误");
        }
        return ServiceResponse.createBySuccessResultMsg("校验成功");
    }

    @Override
    public String selectQuestion(String username) {
        return null;
    }

    @Override
    public String checkAnswer(String username, String question, String answer) {
        return null;
    }

    @Override
    public String forgetResetPassword(String username, String passwordNew, String forgetToken) {
        return null;
    }

    @Override
    public String resetPassword(String passwordOld, String passwordNew, UserReqDto userReqDto) {
        return null;
    }

    @Override
    public UserRespDto updateInformation(UserReqDto userReqDto) {
        return null;
    }

    @Override
    public UserRespDto getInformation(Integer UserId) {
        return null;
    }

    @Override
    public void checkAdminRole(UserReqDto userReqDto) {

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
