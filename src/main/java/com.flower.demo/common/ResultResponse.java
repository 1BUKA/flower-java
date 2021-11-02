package com.flower.demo.common;

import com.alibaba.fastjson.JSON;
import com.flower.demo.common.response.CommonResponseCodeEnum;
import com.flower.demo.common.response.ResponseCode;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class ResultResponse<T> implements Serializable {
    /**
     * 请求id
     */
    private String requestId;
    /**
     * 业务错误码
     */
    private String code;
    /**
     * 消息
     */
    private String message;
    /**
     * 是否成功
     */
    private Boolean success;
    /**
     * 数据
     */
    private T data;

    public ResultResponse() {
        this.requestId = UUID.randomUUID().toString();
    }

    public static <T> ResultResponse<T> succResult() {
        ResultResponse<T> resultResponse = new ResultResponse<T>();
        resultResponse.setCode(CommonResponseCodeEnum.SUCCESS.getCode());
        resultResponse.setSuccess(true);
        resultResponse.setMessage(CommonResponseCodeEnum.SUCCESS.getMessage());
        return resultResponse;
    }

    public static <T> ResultResponse<T> succResult(T data) {
        ResultResponse<T> resultDTO = new ResultResponse<T>();
        resultDTO.setCode(CommonResponseCodeEnum.SUCCESS.getCode());
        resultDTO.setSuccess(true);
        resultDTO.setMessage(CommonResponseCodeEnum.SUCCESS.getMessage());
        resultDTO.setData(data);
        return resultDTO;
    }

    public static <T> ResultResponse<T> succResult(String reqId, T data) {
        ResultResponse<T> resultResponse = new ResultResponse<T>();
        resultResponse.setRequestId(reqId);
        resultResponse.setCode(CommonResponseCodeEnum.SUCCESS.getCode());
        resultResponse.setSuccess(true);
        resultResponse.setMessage(CommonResponseCodeEnum.SUCCESS.getMessage());
        resultResponse.setData(data);
        return resultResponse;
    }

    public static <T> ResultResponse<T> errorResult(String message) {
        return errorResult("500", message);
    }

    public static <T> ResultResponse<T> errorResult(String message, T data) {
        ResultResponse<T> resultDTO = new ResultResponse<T>();
        resultDTO.setCode("500");
        resultDTO.setData(data);
        resultDTO.setMessage(message);
        resultDTO.setSuccess(false);
        return resultDTO;
    }

    public static <T> ResultResponse<T> errorResult(String code, String message) {
        ResultResponse<T> resultDTO = new ResultResponse<T>();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        resultDTO.setSuccess(false);
        return resultDTO;
    }

    public static <T> ResultResponse<T> errorResult(ResponseCode responseCode) {
        ResultResponse<T> resultDTO = new ResultResponse<T>();
        resultDTO.setCode(responseCode.getCode());
        resultDTO.setMessage(responseCode.getMessage());
        resultDTO.setSuccess(false);
        return resultDTO;
    }

    public static <T> ResultResponse<T> errorResult(ResponseCode responseCode, String message) {
        ResultResponse<T> resultDTO = new ResultResponse<T>();
        resultDTO.setCode(responseCode.getCode());
        resultDTO.setMessage(message);
        resultDTO.setSuccess(false);
        return resultDTO;
    }

    public static <T> ResultResponse<T> errorResult(String reqId, String code, String message) {
        ResultResponse<T> resultResponse = new ResultResponse<T>();
        resultResponse.setRequestId(reqId);
        resultResponse.setCode(code);
        resultResponse.setMessage(message);
        resultResponse.setSuccess(false);
        return resultResponse;
    }

    public static <T> ResultResponse<T> errorResult(String reqId, String code, String message, T data) {
        ResultResponse<T> resultResponse = new ResultResponse<T>();
        resultResponse.setRequestId(reqId);
        resultResponse.setCode(code);
        resultResponse.setMessage(message);
        resultResponse.setSuccess(false);
        resultResponse.setData(data);
        return resultResponse;
    }


    public static <T> ResultResponse<T> notFound(String message) {
        return errorResult("404", message);
    }

    public static <T> ResultResponse<T> alreadyExist(String message) {
        return errorResult("409", message);
    }

    public static <T> ResultResponse<T> invalidParam(String param) {
        return errorResult("400", param);
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }


    /*public boolean isError() {
        return BooleanUtil.isFalse(this.success);
    }*/
}
