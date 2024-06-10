package com.example.team_12_be.member.domain.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@Builder
public class MemberResponse {
    private Long id;
    private String email;
    private String nickName;
    private String aboutMe;
    private Job job;
    private String token;
    public MemberResponse(Long id, String email, String nickName, String aboutMe, Job job, String token) {
        this.id = id;
        this.email = email;
        this.nickName = nickName;
        this.aboutMe = aboutMe;
        this.job = job;
        this.token = token;
    }
}