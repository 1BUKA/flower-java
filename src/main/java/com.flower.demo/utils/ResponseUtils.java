package com.flower.demo.utils;

import com.flower.demo.common.response.CommonResponseCodeEnum;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public class ResponseUtils {
    public static CommonResponseCodeEnum getCommonResponseCodeEnumByCode(String code){
        for (CommonResponseCodeEnum item: CommonResponseCodeEnum.values()) {
            if(item.getCode().equals(code)){
                return item;
            }
        }
        return null;
    }
}
