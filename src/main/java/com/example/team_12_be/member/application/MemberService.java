package com.example.team_12_be.member.application;

import com.example.team_12_be.member.domain.Member;
import com.example.team_12_be.member.domain.MemberRepository;
import com.example.team_12_be.member.domain.MemberTechStack;
import com.example.team_12_be.member.domain.MemberTechStackRepository;
import com.example.team_12_be.member.domain.vo.MemberRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberTechStackRepository memberTechStackRepository;

    public Member registerMemberWithTechStack(MemberRequest memberRequest) {

        Member member = Member.builder()
                .email(memberRequest.getEmail())
                .nickName(memberRequest.getNickName())
                .aboutMe(memberRequest.getAboutMe())
                .isDeleted(false)
                .build();
        memberRepository.save(member);
        System.out.println("TechStack = " + memberRequest.getTechStack());
        MemberTechStack memberTechStack = new MemberTechStack(memberRequest.getTechStack());
        member.addTechStack(memberTechStack);
        memberTechStackRepository.save(memberTechStack);

        return member;
    }
    public Member findById(Long id) {
        return memberRepository.findById(id).orElseThrow();
    }

    public Optional<Member> findByEmail(String email) { return memberRepository.findByEmail(email);
    }

}
