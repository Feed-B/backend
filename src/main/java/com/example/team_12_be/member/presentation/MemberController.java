package com.example.team_12_be.member.presentation;

import com.example.team_12_be.member.application.MemberService;
import com.example.team_12_be.member.domain.Member;
import com.example.team_12_be.member.domain.vo.MemberRequest;
import com.example.team_12_be.member.domain.vo.MemberResponse;
import com.example.team_12_be.member.exception.MemberAlreadyExistsException;
import com.example.team_12_be.security.CustomUserDetails;
import com.example.team_12_be.security.JwtProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jdk.jfr.Description;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Tag(name = "회원가입 토큰 발급 컨트롤러")
@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

        private final MemberService memberService;
        private final JwtProvider jwtProvider;

        @GetMapping("/login/{service}")
        @Operation(description = "{service} = kakao 또는 naver ")
        public RedirectView login(@PathVariable("service") String service) {
                String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
                String redirectUrl = "";

                switch (service.toLowerCase()) {
                        case "kakao" :
                                redirectUrl = baseUrl + "/oauth2/authorization/kakao";
                                break;
                        case "naver" :
                                redirectUrl = baseUrl + "/oauth2/authorization/naver";
                                break;
                        default:
                                throw new IllegalArgumentException("Unsupported service : " + service);
                }

                return new RedirectView(redirectUrl);
        }
        //TODO 인증된 정보로 Member 가져오는 예제 코드(추후 삭제
        @GetMapping("/member-info")
        public void memberInfo(@AuthenticationPrincipal CustomUserDetails userDetails){
                Member member = userDetails.getMember();
                log.info(member.toString());
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

                MemberResponse memberResponse = new MemberResponse(member.getId(), member.getEmail() , member.getNickName() , member.getAboutMe() , member.getMemberJob() , token);
                return ResponseEntity.status(HttpStatus.CREATED).body(memberResponse);
        }
}




