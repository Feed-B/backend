package com.example.team_12_be.member.domain;

import com.example.team_12_be.base.TimeStamp;
import com.example.team_12_be.member.domain.vo.AboutMe;
import com.example.team_12_be.member.domain.vo.MemberEmail;
import com.example.team_12_be.member.domain.vo.NickName;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
public class Member extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private MemberEmail email;

    @Embedded
    private NickName nickName;

    @Embedded
    private AboutMe aboutMe;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MemberTechStack> memberTechStacks = new LinkedHashSet<>();

    public Member(MemberEmail email, NickName nickName, AboutMe aboutMe) {
        this.email = email;
        this.nickName = nickName;
        this.aboutMe = aboutMe;
    }
}
