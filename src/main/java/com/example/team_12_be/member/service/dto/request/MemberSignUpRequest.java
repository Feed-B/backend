package com.example.team_12_be.member.service.dto.request;

import com.example.team_12_be.member.domain.Member;
import com.example.team_12_be.member.domain.vo.Job;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


public record MemberSignUpRequest (

    String email,
    String nickName,
    String aboutMe,
    Job job

) {
    public Member toEntity() {
        return new Member(
                email,
                nickName,
                aboutMe,
                job
        );
    }
}
