package com.example.team_12_be.member.domain.vo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NickNameTest {
    @Test
    void testConstructorWithNullNickNameThrowsIllegalArgumentException() {
        assertThatThrownBy(() -> new NickName(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testEmptyNickNameThrowsIllegalArgumentException() {
        assertThatThrownBy(() -> new NickName("")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testLongNickNameThrowsIllegalArgumentException() {
        String overLengthNickName = "ThisIsMoreThan8Characters";

        assertThatThrownBy(() -> new NickName(overLengthNickName)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testValidNickNameCreation() {
        String value = "NickName";
        NickName nickName = new NickName(value);

        assertThat(nickName.getValue()).isNotNull();
        assertThat(nickName.getValue()).isEqualTo(value);
    }
}