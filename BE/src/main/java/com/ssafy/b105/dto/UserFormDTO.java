package com.ssafy.b105.dto;


import com.ssafy.b105.entity.User;
import com.ssafy.b105.entity.UserRole;
import lombok.Getter;


@Getter
public class UserFormDTO {

    private String principal;

    private String name;

    private String credential;

    private String phone;

    private final UserRole role = UserRole.SUPPORTER;

    public User toEntity() {
        return User.builder()
                .principal(this.principal)
                .name(this.name)
                .credential(this.credential)
                .phone(this.phone)
                .role(this.role)
                .build();
    }
}
