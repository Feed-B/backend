package com.example.team_12_be.member.domain;

import com.example.team_12_be.base.TimeStamp;
import com.example.team_12_be.member.domain.vo.Job;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@NoArgsConstructor
@Getter
@SQLDelete(sql = "UPDATE MEMBER SET IS_DELETED = true WHERE id = ?")
@SQLRestriction("IS_DELETED = false")
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

    @Enumerated(EnumType.STRING)
    private Job memberJob;

    public Member(String email, String nickName, String aboutMe, Job memberJob) {
        this.email = email;
        this.nickName = nickName;
        this.aboutMe = aboutMe;
        this.memberJob = memberJob;
    }
}