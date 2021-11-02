package com.flower.demo.service.impl;

import com.flower.demo.entity.*;
import com.flower.demo.mapper.RoleMapper;
import com.flower.demo.mapper.TUserMapper;
import com.flower.demo.mapper.UserConnRoleMapper;
import com.flower.demo.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private TUserMapper userMapper;
    @Resource
    private UserConnRoleMapper userConnRoleMapper;
    @Resource
    private RoleMapper roleMapper;

    @Override
    public UserInfo getUserInfoByPhone(String s)  {

        TUser tUser = getUserByPhone(s);
        if (Objects.isNull(tUser)) {
            return null;
        }
        Integer id = tUser.getId();
        Role role = getRoleByUserId(id);
        if (Objects.isNull(role)) {
            return null;
        }
        UserInfo userInfo = userToUserInfo(tUser, role);

        return userInfo;
    }

    public UserInfo userToUserInfo(TUser user, Role role) {
        UserInfo userInfo = new UserInfo();
        userInfo.setPassword(user.getPassword());
        userInfo.setId(user.getId());
        userInfo.setUserPhone(user.getUserPhone());
        userInfo.setRoleId(role.getId());
        userInfo.setRole(role.getName());
        return userInfo;
    }

    public TUser getUserByPhone(String phone) {
        TUserExample example = new TUserExample();
        TUserExample.Criteria criteria = example.createCriteria();
        criteria.andUserPhoneEqualTo(phone);
        List<TUser> tUsers = userMapper.selectByExample(example);
        if (tUsers.size() <= 0) {
            return null;
        }

        return tUsers.get(0);
    }

    public Role getRoleByUserId(Integer userId) {
        UserConnRoleExample example = new UserConnRoleExample();
        UserConnRoleExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<UserConnRole> userConnRoles = userConnRoleMapper.selectByExample(example);
        if (userConnRoles.size() < 0) {
            return null;
        }
        Integer roleId = userConnRoles.get(0).getRoleId();
        Role role = roleMapper.selectByPrimaryKey(roleId);
        return role;
    }
}
