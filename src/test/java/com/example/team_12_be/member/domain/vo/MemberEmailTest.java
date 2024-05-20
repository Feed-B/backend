package com.example.team_12_be.member.domain.vo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemberEmailTest {

    @Test
    void testConstructorWithNullEmailThrowsIllegalArgumentException() {
        assertThatThrownBy(() -> new MemberEmail(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testEmptyEmailThrowsIllegalArgumentException() {
        assertThatThrownBy(() -> new MemberEmail("")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testInvalidEmail() {
        String noAtSign = "invalidemail.com";;
        String invalidDomain = "invalid2@emailcom";

        assertThatThrownBy(() -> new MemberEmail(noAtSign)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new MemberEmail(invalidDomain)).isInstanceOf(IllegalArgumentException.class);
    }
}