package com.example.team_12_be.project.service;

import com.example.team_12_be.member.domain.Member;
import com.example.team_12_be.project.domain.Project;
import com.example.team_12_be.project.domain.ProjectRating;
import com.example.team_12_be.project.domain.vo.StarRank;
import com.example.team_12_be.project.repository.ProjectRatingJpaRepository;
import com.example.team_12_be.project.service.dto.request.ProjectRatingRequestDto;
import com.example.team_12_be.project.service.dto.request.ProjectRatingUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectRatingService {

    private final ProjectRatingJpaRepository projectRatingJpaRepository;

    private ProjectRating getProjectRating(Long projectRatingId) {
        return projectRatingJpaRepository.findById(projectRatingId).orElseThrow(
                () -> new IllegalArgumentException("없는 레이팅")
        );
    }

    public void addRating(Project project, Member member, ProjectRatingRequestDto projectRatingRequestDto) {
        ProjectRating projectRating = new ProjectRating(member, project, projectRatingRequestDto.toStarRank());
        project.addProjectRating(projectRating);
    }

    public void modifyRating(Long authorId, ProjectRatingUpdateRequestDto projectRatingUpdateRequestDto) {
        ProjectRating projectRating = getProjectRating(projectRatingUpdateRequestDto.projectRatingId());
        if (!projectRating.getMember().getId().equals(authorId)){
            throw new IllegalArgumentException("내 레이팅만 변경 가능");
        }
        StarRank starRank = projectRatingUpdateRequestDto.toStarRank();
        projectRating.updateRank(starRank);
    }

    public void deleteRating(Long authorId, Project project) {
        ProjectRating projectRating = projectRatingJpaRepository.findByMemberIdAndProjectId(authorId, project.getId())
                .orElseThrow(
                        () -> new IllegalArgumentException("없는 레이팅")
                );
        if (!projectRating.getMember().getId().equals(authorId)){
            throw new IllegalArgumentException("내 레이팅만 삭제 가능");
        }
        project.removeProjectRating(projectRating);
    }
}
