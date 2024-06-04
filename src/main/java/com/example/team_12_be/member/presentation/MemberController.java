package com.example.team_12_be.member.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MemberController {

        @GetMapping("/loginForm")
        public String loginForm() {
            return "redirect:/loginForm.html";
        }
    }

