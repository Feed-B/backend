package com.example.team_12_be.member.domain;

import com.example.team_12_be.member.domain.vo.TechStackValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class MemberTechStack {

    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private TechStackValue techStack;

    @ManyToOne(fetch = FetchType.LAZY) // LAZY 로 하지 않아도 무방할듯
    @JoinColumn(name = "member_id")
    private Member member;

}
