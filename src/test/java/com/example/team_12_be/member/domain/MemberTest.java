package com.example.team_12_be.member.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MemberTest {
    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        Member member = new Member("test", "test", "test");

        memberRepository.saveAndFlush(member);
    }

    @Test
    void softDeleteTest() {
        Member member = memberRepository.findByEmail("test").orElseThrow();
        memberRepository.delete(member);

        Member reloadedMember = memberRepository.findByIdIncludingDeleted(member.getId()).orElseThrow();

        assertThat(memberRepository.existsById(member.getId())).isTrue();
        assertThat(reloadedMember.getIsDeleted()).isTrue();
    }
}