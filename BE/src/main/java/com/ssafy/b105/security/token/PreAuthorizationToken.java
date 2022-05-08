package com.ssafy.b105.security.token;

import com.ssafy.b105.dto.UserFormDTO;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class PreAuthorizationToken extends UsernamePasswordAuthenticationToken {

    private PreAuthorizationToken(String principal, String password) {
        super(principal, password);
    }

    public PreAuthorizationToken(UserFormDTO dto) {
        this(dto.getPrincipal(), dto.getCredential());
    }

    public String getUsername() {
        return (String) super.getPrincipal();
    }

    public String getUserPassword() {
        return (String) super.getCredentials();
    }
}
