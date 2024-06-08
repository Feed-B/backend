package com.example.team_12_be.security.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

@Component
public class OAuth2AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String email = null;
        if (exception instanceof OAuth2UserNotFoundException) {
            email = ((OAuth2UserNotFoundException) exception).getEmail();
        }

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        PrintWriter writer = response.getWriter();
        //계정 없을 때와 기본 인증 에러간의 응답 구분
        if (email != null) {
            writer.write("{\"type\":\"signUp\", \"email\":\"" + email + "\"}");
        } else {
            writer.write("{\"error\":\"Unauthorized\"}");
        }
        writer.flush();
    }
}
