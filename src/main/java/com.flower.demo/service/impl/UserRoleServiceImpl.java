package com.flower.demo.service.impl;

import com.flower.demo.entity.Role;
import com.flower.demo.mapper.RoleMapper;
import com.flower.demo.service.UserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Resource
    private RoleMapper roleMapper;

    @Override
    public String getRoleNameById(Integer roleId){
        Role role = roleMapper.selectByPrimaryKey(roleId);
        return role.getName();
    }
}
