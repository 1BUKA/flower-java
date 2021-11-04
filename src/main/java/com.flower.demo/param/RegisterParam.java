package com.flower.demo.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RegisterParam {
    @NotNull
    private String userPhone;
    @NotNull
    private String password;
}
