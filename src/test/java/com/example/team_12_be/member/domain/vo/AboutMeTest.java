package com.example.team_12_be.member.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AboutMeTest {

    @Test
    @DisplayName("회원 소개는 150자를 초과할 수 없다.")
    void testAboutMeExceedsMaxCharacterLimit() {
        String tooLongText = ".".repeat(151);

        assertThatThrownBy(() -> new AboutMe(tooLongText))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("회원 소개는 150자 이하일 때 유효하다")
    void testValidAboutMe() {
        String validText = ".".repeat(150);
        AboutMe validAboutMe = new AboutMe(validText);

        assertThat(validAboutMe).isNotNull();
        assertThat(validAboutMe.getValue()).isEqualTo(validText);
    }
}