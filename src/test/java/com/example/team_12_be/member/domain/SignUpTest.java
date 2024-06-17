//package com.example.team_12_be.member.domain;
//
//import com.example.team_12_be.member.application.MemberService;
//import com.example.team_12_be.member.domain.vo.Job;
//import com.example.team_12_be.member.domain.vo.MemberRequest;
//import org.junit.jupiter.api.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest
//public class SignUpTest {
//    private static final Logger logger = LoggerFactory.getLogger(SignUpTest.class);
//
//    @Autowired
//    MemberService memberService;
//
//    @Test
//    void signUpTest() {
//        MemberRequest memberRequest = new MemberRequest();
//        memberRequest.setEmail("gkstjr8332@naver.com");
//        memberRequest.setNickName("김한석");
//        memberRequest.setAboutMe("나는 김한석입니다.");
//        memberRequest.setJob(Job.BACKEND);
//
//        Member saveMember = memberService.registerMemberWithTechStack(memberRequest);
//
//
//        assertEquals(memberRequest.getEmail(),saveMember.getEmail());
//        assertEquals(memberRequest.getNickName(),saveMember.getNickName());
//        assertEquals(memberRequest.getAboutMe(),saveMember.getAboutMe());
//        assertEquals(memberRequest.getJob(),saveMember.getMemberJob());
//        assertEquals(1L ,saveMember.getId());
//
//        MemberRequest memberRequest2 = new MemberRequest();
//        memberRequest2.setEmail("gkstjr8332@kakao.com");
//        memberRequest2.setNickName("김한석2");
//        memberRequest2.setAboutMe("나는 김한석입니다.");
//        memberRequest2.setJob(Job.BACKEND);
//
//        Member saveMember2 = memberService.registerMemberWithTechStack(memberRequest2);
//
//
//        assertEquals(memberRequest2.getEmail(),saveMember2.getEmail());
//        assertEquals(memberRequest2.getNickName(),saveMember2.getNickName());
//        assertEquals(memberRequest2.getAboutMe(),saveMember2.getAboutMe());
//        assertEquals(memberRequest2.getJob(),saveMember2.getMemberJob());
//        assertEquals(2L ,saveMember2.getId());
//    }
//}
