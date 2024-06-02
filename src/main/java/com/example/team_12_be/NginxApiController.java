package com.example.team_12_be;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequiredArgsConstructor
public class NginxApiController {

    private final Environment env;

    //무중단 배포 메인 페이지 테스트용(임시)
    @RequestMapping("/")
    public String hello() {
        return "완료";
    }

    @GetMapping("/nginx/profile")
    public String getProfile() {
        return Arrays.stream(env.getActiveProfiles())
                .findFirst()
                .orElse("");
    }
}
