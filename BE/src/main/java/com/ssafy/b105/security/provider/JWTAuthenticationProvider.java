package com.ssafy.b105.security.provider;

import com.ssafy.b105.dto.UserDTO;
import com.ssafy.b105.security.jwt.JwtDecoder;
import com.ssafy.b105.security.token.JwtPreProcessingToken;
import com.ssafy.b105.security.token.PostAuthorizationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JWTAuthenticationProvider implements AuthenticationProvider {

    private final JwtDecoder jwtDecoder;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        String     token      = (String) authentication.getPrincipal();
        UserDTO userDTO = jwtDecoder.decodeJwt(token);

        return PostAuthorizationToken.getTokenFormUserDetails(userDTO);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtPreProcessingToken.class.isAssignableFrom(authentication);
    }
}