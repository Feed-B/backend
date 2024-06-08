package com.example.team_12_be.member.presentation;

import com.example.team_12_be.member.application.MemberService;
import com.example.team_12_be.member.domain.Member;
import com.example.team_12_be.member.domain.MemberRepository;
import com.example.team_12_be.member.domain.MemberTechStack;
import com.example.team_12_be.member.domain.vo.MemberRequest;
import com.example.team_12_be.member.domain.vo.MemberResponse;
import com.example.team_12_be.member.exception.MemberAlreadyExistsException;
import com.example.team_12_be.security.CustomUserDetails;
import com.example.team_12_be.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MemberController {

        private final MemberService memberService;
        private final JwtProvider jwtProvider;

        //TODO 인증된 정보로 Member 가져오는 예제 코드(추후 삭제
        @GetMapping("/member-info")
        public void memberInfo(@AuthenticationPrincipal CustomUserDetails userDetails){
                Member member = userDetails.getMember();
                System.out.println(member.toString());
        }

        //회원가입
        @PostMapping("/signUp")
        public ResponseEntity<MemberResponse> signUp(@RequestBody MemberRequest memberRequest) {
                Optional<Member> findMember = memberService.findByEmail(memberRequest.getEmail());
                if(findMember.isPresent() && !findMember.get().getIsDeleted()) {
                        throw new MemberAlreadyExistsException("Email is already in use.");
                }

                Member member = memberService.registerMemberWithTechStack(memberRequest);

                //토큰 생성
                String token = jwtProvider.createToken(memberRequest.getEmail());

                MemberResponse memberResponse = new MemberResponse(member.getId(), member.getEmail() , member.getNickName() , member.getAboutMe() , memberRequest.getTechStack() , token);
                return ResponseEntity.status(HttpStatus.CREATED).body(memberResponse);
        }
}



