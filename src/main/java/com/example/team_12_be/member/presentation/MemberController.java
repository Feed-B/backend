package com.example.team_12_be.member.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MemberController {
        //TODO 백 단 자체 oauth2 테스트 위한 메소드(추후 삭제)
        @GetMapping("/loginForm")
        public String loginForm() {
            return "redirect:/loginForm.html";
        }


}

