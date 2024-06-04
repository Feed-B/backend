package com.example.team_12_be.project.service;

import com.example.team_12_be.member.application.MemberService;
import com.example.team_12_be.member.domain.Member;
import com.example.team_12_be.project.domain.Project;
import com.example.team_12_be.project.domain.ProjectRating;
import com.example.team_12_be.project.domain.ProjectRepository;
import com.example.team_12_be.project.service.dto.request.ProjectRatingRequestDto;
import com.example.team_12_be.project.service.dto.request.ProjectRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    private final MemberService memberService;

    public void saveProject(ProjectRequestDto projectRequestDto, Member author) {
        Project project = projectRequestDto.toEntity(author);
        projectRepository.saveProject(project);
    }

    public void deleteProject(Long projectId) {
        projectRepository.deleteById(projectId);
    }

    public void addRating(Long projectId, Long memberId, ProjectRatingRequestDto projectRatingRequestDto) {
        boolean ratingExists = projectRepository.existsRatingByMemberAndProject(memberId, projectId);
        if (ratingExists) {
            throw new IllegalArgumentException("이미 존재한다");
        }

        Member member = memberService.findById(memberId);
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 프로젝트이다."));

        ProjectRating projectRating = new ProjectRating(member, project, projectRatingRequestDto.toStarRank());
        projectRepository.saveProjectRating(projectRating);
        project.addProjectRatings(projectRating);
    }
}
