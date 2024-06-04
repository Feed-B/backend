package com.example.team_12_be.member.domain;

import com.example.team_12_be.base.TimeStamp;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberTechStack> memberTechStacks = new ArrayList<>();

    public Member(String email, String nickName, String aboutMe) {
        this.email = email;
        this.nickName = nickName;
        this.aboutMe = aboutMe;
    }

    public void addTechStack(MemberTechStack techStack) {
        memberTechStacks.add(techStack);
        techStack.setMember(this);
    }

    public void removeTechStack(MemberTechStack techStack) {
        memberTechStacks.remove(techStack);
        techStack.setMember(null);
    }
}