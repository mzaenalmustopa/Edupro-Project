package org.edupro.webapi.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.edupro.webapi.constant.MessageApp;
import org.edupro.webapi.base.model.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(403);
        response.getWriter().write(ResponseError.builder()
                        .statusCode(HttpStatus.FORBIDDEN.value())
                        .message(MessageApp.FORBIDDEN)
                        .errors(MessageApp.FORBIDDEN)
                .build()
                .toString());
    }
}
