package com.flower.demo.service.impl;

import com.flower.demo.common.response.CommonResponseCodeEnum;
import com.flower.demo.config.security.JwtTokenUtil;
import com.flower.demo.entity.TUser;
import com.flower.demo.entity.TUserExample;
import com.flower.demo.entity.UserConnRole;
import com.flower.demo.mapper.TUserMapper;
import com.flower.demo.mapper.UserConnRoleMapper;
import com.flower.demo.param.LoginParam;
import com.flower.demo.param.RegisterParam;
import com.flower.demo.service.LoginService;
import com.flower.demo.utils.RedisUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.xwpf.usermodel.TOC;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private TUserMapper tUserMapper;
    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private JwtTokenUtil jwtTokenUtil;
    @Resource
    private RedisUtils redisUtils;
    @Resource
    private UserConnRoleMapper userConnRoleMapper;

    @Override
    public String login(LoginParam param) {
        String phone = param.getUserPhone();
        Optional<TUser> tUserOptional = getTUserByPhone(phone);
        if (!tUserOptional.isPresent()) {
            return CommonResponseCodeEnum.USER_NOT_EXIST.getMessage();
        }

        TUser tUser = tUserOptional.get();
        String password = param.getPassword();
        if (!tUser.getPassword().equals(password)) {
            return CommonResponseCodeEnum.ACCOUNT_PASSWORD_ERROR.getMessage();
        }
        String token = getToken(phone);
        return token;
    }

    @Override
    public String register(RegisterParam param) throws InvocationTargetException, IllegalAccessException {
        String userPhone = param.getUserPhone();
        if(isRegistered(userPhone)){
            return CommonResponseCodeEnum.ACCOUNT_EXIST.getMessage();
        }
        TUser tUser = new TUser();
        BeanUtils.copyProperties(tUser, param);
        tUser.setMoney(1000);
        System.out.println("插入的数据为\t" + tUser.toString());
        int insert = tUserMapper.insert(tUser);
        boolean b = insertUserConnRole(userPhone);
        if (insert == 1 && b) {
            return null;
        }
        return CommonResponseCodeEnum.FAIL_REGISTER.getMessage();
    }

    private Boolean isRegistered(String userPhone){
        TUserExample example = new TUserExample();
        TUserExample.Criteria criteria = example.createCriteria();
        criteria.andUserPhoneEqualTo(userPhone);
        List<TUser> tUsers = tUserMapper.selectByExample(example);
        if(tUsers.size() > 0){
            return true;
        }
        return false;
    }
    private boolean insertUserConnRole(String userPhone){
        TUserExample example = new TUserExample();
        TUserExample.Criteria criteria = example.createCriteria();
        criteria.andUserPhoneEqualTo(userPhone);
        List<TUser> tUsers = tUserMapper.selectByExample(example);
        if(tUsers.size() == 0){
            return false;
        }
        TUser tUser = tUsers.get(0);
        Integer id = tUser.getId();
        UserConnRole userConnRole = new UserConnRole();
        userConnRole.setRoleId(1);
        userConnRole.setUserId(id);
        int insert = userConnRoleMapper.insert(userConnRole);
        if(insert == 1){
            return true;
        }
        return false;
    }

    private String getToken(String phone) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(phone);
        String token = jwtTokenUtil.generateToken(userDetails);
        redisUtils.set(token, phone);
        return token;
    }

    private Optional<TUser> getTUserByPhone(String phone) {
        TUserExample example = new TUserExample();
        TUserExample.Criteria criteria = example.createCriteria();
        criteria.andUserPhoneEqualTo(phone);

        List<TUser> tUsers = tUserMapper.selectByExample(example);
        if (tUsers.size() == 0) {
            return Optional.empty();
        }
        Optional<TUser> tUser = Optional.of(tUsers.get(0));
        return tUser;
    }

}
