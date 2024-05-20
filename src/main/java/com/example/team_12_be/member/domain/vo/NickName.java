package com.example.team_12_be.member.domain.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
public class NickName {

    @Column(name = "nick_name", unique = true, length = 8)
    private String value;

    public NickName(String value) {
        if (value == null || value.isEmpty()){
            throw new IllegalArgumentException("닉네임은 필수값입니다.");
        }
        if (value.length() > 8){
            throw new IllegalArgumentException("닉네임 길이는 8자를 초과해서는 안됩니다.");
        }
        this.value = value;
    }
}
