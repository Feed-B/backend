package com.example.team_12_be.member.domain.vo;

import com.example.team_12_be.member.domain.MemberTechStack;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MemberRequest {

    private String email;
    private String nickName;
    private String aboutMe;
    private TechStackValue techStack;

}