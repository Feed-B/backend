package com.example.team_12_be.project.service;

import com.example.team_12_be.member.domain.Member;
import com.example.team_12_be.member.domain.MemberRepository;
import com.example.team_12_be.member.domain.vo.Job;
import com.example.team_12_be.project.domain.Project;
import com.example.team_12_be.project.repository.ProejctJpaRepository;
import com.example.team_12_be.project.service.dto.request.ProjectLinkRequestDto;
import com.example.team_12_be.project.service.dto.request.ProjectRequestDto;
import com.example.team_12_be.project.service.dto.request.ProjectTeammateRequestDto;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@Transactional
class ProjectServiceTest {

    @Autowired
    ProjectService projectService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ProejctJpaRepository proejctJpaRepository;

    ProjectRequestDto requestDtoFixture;

    Member memberFixture;

    @BeforeEach
    void setup(){
        memberFixture = createMemberFixture();
        requestDtoFixture = createTestFixture();
        memberRepository.save(memberFixture);
    }

    Member createMemberFixture(){
        return new Member(
                "test",
                "test",
                "test",
                Job.IOS
        );
    }

    ProjectRequestDto createTestFixture() {
        String title = "Test Title";
        String introduction = "Test Introduction";
        String content = "Test Content";
        String serviceUrl = "http://localhost:8080";
        List<String> projectTechStacks = Arrays.asList("Java", "Spring", "JUnit");
        List<ProjectTeammateRequestDto> projectTeammates = new ArrayList<>();
        List<ProjectLinkRequestDto> projectLinks = new ArrayList<>();

        return new ProjectRequestDto(
                title,
                introduction,
                content,
                serviceUrl,
                projectTechStacks,
                projectTeammates,
                projectLinks
        );
    }

//    @Test
//    void testSave() {
//        projectService.saveProject(requestDtoFixture, memberFixture);
//
//        Project project = proejctJpaRepository.findAll().getFirst();
//
//        SoftAssertions.assertSoftly(
//                softly -> {
//                    softly.assertThat(project.getProjectTechStacks()).hasSameSizeAs(requestDtoFixture.projectTechStacks());
//                    softly.assertThat(project.getProjectTeammates()).hasSameSizeAs(requestDtoFixture.projectTeammates());
//                    softly.assertThat(project.getProjectLinks()).hasSameSizeAs(requestDtoFixture.projectLinks());
//                }
//        );
//    }

}