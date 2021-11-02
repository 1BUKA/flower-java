package com.flower.demo.entity;

import lombok.Data;

@Data
public class UserInfo {

    private int id;
    private String username;
    private String userPhone;
    private Integer roleId;
    private String password;
    private String role;
}
