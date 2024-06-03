package com.example.team_12_be.project.comment.repository.comment;

import com.example.team_12_be.project.comment.domain.ProjectComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectCommentJpaRepository extends JpaRepository<ProjectComment, Long> {

    Page<ProjectComment> findAllByProjectId(Long projectId, Pageable pageable);
}
