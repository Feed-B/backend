package com.example.team_12_be.security;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class OAuth2MemberService implements OAuth2UserService<OAuth2UserRequest , OAuth2User> {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        DefaultOAuth2UserService delegeate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegeate.loadUser(userRequest);
        System.out.println("oAuth2User = " + oAuth2User); //디버깅
        return oAuth2User;
    }
}
