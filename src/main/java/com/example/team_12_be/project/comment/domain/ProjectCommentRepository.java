package com.example.team_12_be.project.comment.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.Optional;

public interface ProjectCommentRepository {

    ProjectComment save(ProjectComment projectComment);

    Optional<ProjectComment> findById(Long id);

    // 혹은 pageAble
    Slice<ProjectComment> findAllByProjectId(Long projectId, Pageable pageable);

}
