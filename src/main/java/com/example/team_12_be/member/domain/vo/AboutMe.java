package com.example.team_12_be.member.domain.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
public class AboutMe {

    @Column(name = "about_me", length = 150)
    private String value;

    public AboutMe(String value) {

        if (value.length() > 150){
            throw new IllegalArgumentException("소개는 150자 이상일 수 없습니다.");
        }

        this.value = value;
    }
}
