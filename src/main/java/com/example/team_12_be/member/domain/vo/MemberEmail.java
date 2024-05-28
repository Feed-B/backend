package com.example.team_12_be.member.domain.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
public class MemberEmail {

    @Column(name = "email")
    private String value;

    public MemberEmail(String value) {
        if (value == null || value.isEmpty()){
            throw new IllegalArgumentException("Email value must not null");
        }

        if (!value.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            throw new IllegalArgumentException("Invalid email format");
        }

        this.value = value;
    }
}
