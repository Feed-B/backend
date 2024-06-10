package com.example.team_12_be.project.service;

import com.example.team_12_be.member.application.MemberService;
import com.example.team_12_be.member.domain.Member;
import com.example.team_12_be.project.domain.*;
import com.example.team_12_be.project.service.dto.request.ProjectLinkRequestDto;
import com.example.team_12_be.project.service.dto.request.ProjectRatingRequestDto;
import com.example.team_12_be.project.service.dto.request.ProjectRequestDto;
import com.example.team_12_be.project.service.dto.request.ProjectTeammateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectService {

    private final ProjectRepository projectRepository;

    private final MemberService memberService;

    public void saveProject(ProjectRequestDto projectRequestDto, Member author) {
        Project project = projectRequestDto.toEntity(author);

        List<ProjectTechStack> techStacks = projectRequestDto.projectTechStacks().stream().map(ProjectTechStack::new).toList();
        techStacks.forEach(each -> each.assign(project));

        List<ProjectTeammate> teammates = projectRequestDto.projectTeammates().stream().map(ProjectTeammateRequestDto::toEntity).toList();
        teammates.forEach(each -> each.assign(project));

        List<ProjectLink> links = projectRequestDto.projectLinks().stream().map(ProjectLinkRequestDto::toEntity).toList();
        links.forEach(each -> each.assign(project));

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
        project.addProjectRating(projectRating);
    }

    public void likeProject(Long memberId, Long projectId){
        boolean isLikeExists = projectRepository.likeExistsByMemberIdAndProjectId(memberId, projectId);

        if (isLikeExists){
            throw new IllegalArgumentException("좋아요가 이미 존재한다");
        }

        Member member = memberService.findById(memberId);
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 프로젝트이다."));

        ProjectLike projectLike = new ProjectLike(member, project);
        project.addProjectLike(projectLike);
    }

    public void unlikeProject(Long memberId, Long projectId){
        boolean isLikeExists = projectRepository.likeExistsByMemberIdAndProjectId(memberId, projectId);

        if (!isLikeExists){
            throw new IllegalArgumentException("좋아요가 존재하지 않는다");
        }

        projectRepository.deleteLikeByMemberIdAndProjectId(memberId, projectId);
    }
}
