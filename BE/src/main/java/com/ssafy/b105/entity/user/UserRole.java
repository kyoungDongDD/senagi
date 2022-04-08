package com.ssafy.b105.entity.user;



public enum UserRole {
    ADMIN("ROLE_ADMIN"),
    SHELTER("ROLE_SHELTER"),
    SUPPORTER("ROLE_SUPPORTER");


    private final String role_user;

    UserRole(String role_user) {
        this.role_user = role_user;
    }


}