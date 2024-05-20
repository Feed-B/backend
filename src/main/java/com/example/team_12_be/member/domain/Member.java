package com.example.team_12_be.member.domain;

import com.example.team_12_be.base.TimeStamp;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
public class Member extends TimeStamp {

    @Id
    private Long id;

    private String email;

    @Column(unique = true, length = 8)
    private String nickName;

    private String introductions;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MemberTechStack> memberTechStacks = new LinkedHashSet<>();

    public Member(String email, String nickName, String introductions) {
        this.email = email;
        this.nickName = nickName;
        this.introductions = introductions;
    }
}
