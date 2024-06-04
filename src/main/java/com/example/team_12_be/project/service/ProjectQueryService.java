package com.example.team_12_be.project.service;

import com.example.team_12_be.project.domain.Project;
import com.example.team_12_be.project.domain.ProjectQueryRepository;
import com.example.team_12_be.project.domain.ProjectRating;
import com.example.team_12_be.project.service.dto.response.ProjectDetailResponseDto;
import com.example.team_12_be.project.service.dto.response.ProjectRatingResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProjectQueryService {

    private final ProjectQueryRepository projectQueryRepository;

    public Project findById(Long id) {
        return projectQueryRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Project with id " + id + " not found")
        );
    }

    public ProjectDetailResponseDto getDetailById(Long projectId) {
        Project project = projectQueryRepository.findById(projectId).orElseThrow(() -> new NoSuchElementException("Project with projectId " + projectId + " not found"));
        return ProjectDetailResponseDto.of(project);
    }

    public ProjectRatingResponseDto getMemberProjectRating(Long memberId, Long projectId) {
        ProjectRating projectRating = projectQueryRepository.findProjectRatingByMemberIdAndProjectId(memberId, projectId).orElseThrow(
                () -> new NoSuchElementException("Project with projectId " + projectId + " not found")
        );

        return ProjectRatingResponseDto.of(projectRating.getStarRank());
    }
}
