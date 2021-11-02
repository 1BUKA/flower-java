package com.flower.demo.service;

import com.flower.demo.entity.UserInfo;

import java.lang.reflect.InvocationTargetException;

public interface UserInfoService {
    UserInfo getUserInfoByPhone(String s) throws InvocationTargetException, IllegalAccessException;
}
