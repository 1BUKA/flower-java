package com.flower.demo.mapper;

import com.flower.demo.entity.UserConnRole;
import com.flower.demo.entity.UserConnRoleExample;

import java.util.List;

public interface UserConnRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserConnRole record);

    int insertSelective(UserConnRole record);

    List<UserConnRole> selectByExample(UserConnRoleExample example);

    UserConnRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserConnRole record);

    int updateByPrimaryKey(UserConnRole record);
}