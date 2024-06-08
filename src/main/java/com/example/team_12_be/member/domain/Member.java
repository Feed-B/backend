package com.example.team_12_be.member.domain;

import com.example.team_12_be.base.TimeStamp;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@SQLDelete(sql = "UPDATE MEMBER SET IS_DELETED = true WHERE id = ?")
@SQLRestriction("IS_DELETED = false")
@Builder
@AllArgsConstructor
@ToString
public class Member extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Boolean isDeleted = false;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String nickName;

    private String aboutMe;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private MemberTechStack memberTechStack;
    public Member(String email, String nickName, String aboutMe) {
        this.email = email;
        this.nickName = nickName;
        this.aboutMe = aboutMe;
    }

    public void addTechStack(MemberTechStack techStack) {
        memberTechStack = techStack;
        techStack.setMember(this);
    }

    public void updateTechStack(MemberTechStack techStack) {
        memberTechStack = techStack;
        techStack.setMember(this);
    }
}