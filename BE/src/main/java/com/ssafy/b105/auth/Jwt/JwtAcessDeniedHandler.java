package com.ssafy.b105.auth.Jwt;


import com.ssafy.b105.auth.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAcessDeniedHandler implements AccessDeniedHandler {

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
    response.setContentType("application/json;charset=UTF-8");
    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    response.getWriter().println("{ \"error\" : \"" + ErrorCode.ACCESS_DENIED.getMessage()
      + "\", \"error_state\" : " + ErrorCode.ACCESS_DENIED.getStatus()
      + "\", \"error_code\" : \"" +  ErrorCode.ACCESS_DENIED.getCode()
      + " }");
  }
}
