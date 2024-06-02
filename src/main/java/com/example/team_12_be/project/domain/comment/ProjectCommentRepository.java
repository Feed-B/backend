package com.example.team_12_be.project.domain.comment;

import java.util.Optional;

public interface ProjectCommentRepository {
    ProjectComment save(ProjectComment projectComment);

    Optional<ProjectComment> findById(Long id);
}
