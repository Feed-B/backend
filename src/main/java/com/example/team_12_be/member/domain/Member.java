package com.example.team_12_be.member.domain;

import com.example.team_12_be.base.TimeStamp;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@SQLDelete(sql = "UPDATE MEMBER SET IS_DELETED = true WHERE IS_DELETED = FALSE AND id = ?")
public class Member extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean isDeleted;

    private String email;

    private String nickName;

    private String aboutMe;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MemberTechStack> memberTechStacks = new LinkedHashSet<>();

    public Member(String email, String nickName, String aboutMe) {
        this.isDeleted = Boolean.FALSE;
        this.email = email;
        this.nickName = nickName;
        this.aboutMe = aboutMe;
    }
}
