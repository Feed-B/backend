package com.example.team_12_be.project.domain;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository {

    Project saveProject(Project project);

    void deleteById(Long projectId);

    Optional<Project> findById(Long projectId);

    boolean existsRatingByMemberAndProject(Long memberId, Long projectId);

    ProjectRating saveProjectRating(ProjectRating projectRating);

    boolean likeExistsByMemberIdAndProjectId(Long memberId, Long projectId);

    ProjectLike saveLike(ProjectLike projectLike);

    void deleteLikeByMemberIdAndProjectId(Long memberId, Long projectId);
}
