package com.example.team_12_be.security.naver.presentation;

import com.example.team_12_be.security.naver.application.NaverService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/naver")
public class NaverController {

    private final NaverService naverService;

    @GetMapping("/callback")
    public String callback(HttpServletRequest request , HttpServletResponse response) throws Exception {
        return null;
    }

}
