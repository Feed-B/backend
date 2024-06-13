package com.example.team_12_be.project.service;

import com.example.team_12_be.member.domain.Member;
import com.example.team_12_be.member.domain.MemberRepository;
import com.example.team_12_be.member.domain.vo.Job;
import com.example.team_12_be.project.domain.Project;
import com.example.team_12_be.project.domain.ProjectTechStack;
import com.example.team_12_be.project.repository.ProejctJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class ProjectUpdateTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ProejctJpaRepository proejctJpaRepository;

    Member memberFixture;

    @BeforeEach
    void setup(){
        memberFixture = createMemberFixture();
        Member member = memberRepository.save(memberFixture);
        Project project = createProjectEntityFixture(member);
        proejctJpaRepository.save(project);
        project.addTechStack(new ProjectTechStack("test"));
        project.addTechStack(new ProjectTechStack("test2"));
    }

    Member createMemberFixture(){
        return new Member(
                "test",
                "test",
                "test",
                Job.IOS
        );
    }

    Project createProjectEntityFixture(Member author) {
        String title = "Test Title";
        String introductions = "Test Introductions";
        String content = "Test Content";
        String serviceUrl = "http://localhost:8080";

        return new Project(
                title,
                introductions,
                content,
                author,
                serviceUrl
        );
    }

    @Test
    @Rollback(false)
    void testRemove() {
        Project project = proejctJpaRepository.findAll().getFirst();
        ProjectTechStack first = project.getProjectTechStacks().getFirst();

        project.removeTechStack(first);

        proejctJpaRepository.save(project);
    }
}
