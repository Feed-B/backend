package com.example.team_12_be.security;

import com.example.team_12_be.member.domain.Member;
import com.example.team_12_be.member.domain.MemberRepository;
import com.example.team_12_be.security.oauth_info.KakaoMemberInfo;
import com.example.team_12_be.security.oauth_info.OAuth2MemberInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OAuth2MemberService implements OAuth2UserService<OAuth2UserRequest , OAuth2User> {

    private final MemberRepository memberRepository;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        DefaultOAuth2UserService delegeate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegeate.loadUser(userRequest);
        OAuth2MemberInfo memberInfo = null;
        System.out.println(oAuth2User.getAttributes());
        System.out.println(userRequest.getClientRegistration().getRegistrationId());

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        System.out.println("registrationId = " + registrationId);
        if(registrationId.equals("kakao")) {
            memberInfo = new KakaoMemberInfo(oAuth2User.getAttributes());
        }//네이버 추가
        else {
            System.out.println("로그인 실패");
        }

        String provider = memberInfo.getProvider();
        String providerId = memberInfo.getProviderId();
        String username = provider + "_" + providerId; //중복이 발생하지 않도록 provider와 providerId를 조합

        String email = memberInfo.getEmail();
        String role = "ROLE_USER"; //TODO 권한 결정 후 변경
        System.out.println("getAttributes = " + oAuth2User.getAttributes() + " , email = " + email +" , userName = " + username);
        Optional<Member> findMember = memberRepository.findByEmail(email);
        Member member = null;
        if(findMember.isEmpty()) {
            member = Member.builder()
                    .nickName(username)
                    .email(email)
                    .aboutMe("추후 변경") //TODO Member 필드 aboutMe 사용 용도 확인 후 수정
                    .build();
            memberRepository.save(member);
        }
        else {
            member = findMember.get();
        }
        return new CustomUserDetails(member , oAuth2User.getAttributes());
    }
}
