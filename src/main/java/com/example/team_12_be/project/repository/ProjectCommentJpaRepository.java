package com.example.team_12_be.project.repository;

import com.example.team_12_be.project.domain.comment.ProjectComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectCommentJpaRepository extends JpaRepository<ProjectComment, Long> {
}
