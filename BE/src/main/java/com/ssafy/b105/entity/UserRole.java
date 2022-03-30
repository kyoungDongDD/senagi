package com.ssafy.b105.entity;

import com.ssafy.b105.constant.EnumModel;

public enum UserRole implements EnumModel {
    ADMIN("ROLE_ADMIN"),
    SHELTER("ROLE_SHELTER"),
    SUPPORTER("ROLE_SUPPORTER");


    private final String role_user;

    UserRole(String role_user) {
        this.role_user = role_user;
    }

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return role_user;
    }
}