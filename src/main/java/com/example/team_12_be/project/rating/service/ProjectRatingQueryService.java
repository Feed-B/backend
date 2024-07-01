package com.example.team_12_be.project.rating.service;

import com.example.team_12_be.project.domain.ProjectRating;
import com.example.team_12_be.project.rating.repository.ProjectRatingJpaRepository;
import com.example.team_12_be.project.rating.service.dto.response.ProjectRatingResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProjectRatingQueryService {

    private final ProjectRatingJpaRepository projectQueryRepository;


    public ProjectRating findRatingById(Long ratingId) {
        return projectQueryRepository.findById(ratingId).orElseThrow(
                () -> new NoSuchElementException("Project with projectId " + ratingId + " not found")
        );
    }

    public ProjectRatingResponseDto getMemberProjectRating(Long memberId, Long projectId) {
        ProjectRating projectRating = projectQueryRepository.findByMemberIdAndProjectId(memberId, projectId).orElseThrow(
                () -> new NoSuchElementException("Project with projectId " + projectId + " not found")
        );

        return ProjectRatingResponseDto.of(projectRating);
    }

    public Long countByProjectId(Long projectId) {
        return projectQueryRepository.countByProjectId(projectId);
    }
}
