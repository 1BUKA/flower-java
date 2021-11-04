package com.flower.demo.controller;

import com.flower.demo.common.ResultResponse;
import com.flower.demo.common.response.CommonResponseCodeEnum;
import com.flower.demo.param.LoginParam;
import com.flower.demo.param.RegisterParam;
import com.flower.demo.service.LoginService;
import com.flower.demo.utils.ResponseUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

@RestController
@RequestMapping("/flower")
public class LoginController {
    @Resource
    private LoginService loginService;

    @PostMapping("login")
    public ResultResponse<Object> login(@Validated @RequestBody LoginParam param) {
        String res = loginService.login(param);
        CommonResponseCodeEnum response = ResponseUtils.getCommonResponseCodeEnumByCode(res);
        if (Objects.isNull(response)) {
            return ResultResponse.succResult(res);
        }
        return ResultResponse.errorResult(response);
    }

    @PostMapping("/register")
    public ResultResponse<Object> register(@Validated @RequestBody RegisterParam param) throws InvocationTargetException, IllegalAccessException {
        String register = loginService.register(param);
        CommonResponseCodeEnum response = ResponseUtils.getCommonResponseCodeEnumByCode(register);
        if (Objects.isNull(response)) {
            return ResultResponse.succResult(register);
        }
        return ResultResponse.errorResult(response);
    }

}
