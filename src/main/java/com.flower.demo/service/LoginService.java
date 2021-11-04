package com.flower.demo.service;

import com.flower.demo.param.LoginParam;
import com.flower.demo.param.RegisterParam;

import java.lang.reflect.InvocationTargetException;

public interface LoginService {
    String login(LoginParam param);

    String register(RegisterParam param) throws InvocationTargetException, IllegalAccessException;
}
