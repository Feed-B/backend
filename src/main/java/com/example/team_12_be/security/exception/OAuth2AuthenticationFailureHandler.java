package com.example.team_12_be.security.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Component
@Slf4j
@RequiredArgsConstructor
public class OAuth2AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private final Environment environment;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String email = null;
        String redirectUrl = environment.getProperty("redirect.base-url") + "/auth/success"; // 프론트엔드에서 처리할 URL

        if (exception instanceof OAuth2UserNotFoundException) {
            email = ((OAuth2UserNotFoundException) exception).getEmail();
        }

        if (email != null) {
            redirectUrl += "?type=signUp&email=" + email;
        } else {
            redirectUrl += "?error=Unauthorized";
        }

        getRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }
}
