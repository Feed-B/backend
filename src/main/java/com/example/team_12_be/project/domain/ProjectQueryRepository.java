package com.example.team_12_be.project.domain;

import java.util.Optional;

public interface ProjectQueryRepository {
    Optional<Project> findById(Long projectId);

    Optional<ProjectRating> findProjectRatingByMemberIdAndProjectId(Long memberId, Long projectId);
}
