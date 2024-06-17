package com.example.team_12_be.project.domain;

import com.example.team_12_be.project.presentation.request.SortCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProjectQueryRepository {
    Optional<Project> findById(Long projectId);

    Optional<ProjectRating> findProjectRatingByMemberIdAndProjectId(Long memberId, Long projectId);

    List<ProjectLike> findLikesByProjectIdWithMember(Long projectId);

    long countLikeByProjectId(Long projectId);

    long countRankByProjectId(Long projectId);

    Page<Project> findProjectsProjectTechStacksOrderBySortCondition(SortCondition sortCondition, List<String> projectTechStacks, Pageable pageable);

    boolean existsLikeByMemberIdAndProjectId(Long memberId, Long projectId);
}
