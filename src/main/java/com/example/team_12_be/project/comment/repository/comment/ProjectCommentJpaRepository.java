package com.example.team_12_be.project.comment.repository.comment;

import com.example.team_12_be.project.comment.domain.ProjectComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProjectCommentJpaRepository extends JpaRepository<ProjectComment, Long> {

    @Query("SELECT pc FROM ProjectComment pc JOIN FETCH pc.member WHERE pc.project.id = :projectId")
    Page<ProjectComment> findAllByProjectIdWithMember(@Param("projectId") Long projectId, Pageable pageable);

    @Query("SELECT pc FROM ProjectComment pc JOIN FETCH pc.member WHERE pc.parentId = :parentId")
    Page<ProjectComment> findAllByParentIdWithMember(@Param("parentId") Long parentId, Pageable pageable);

    Optional<ProjectComment> findByProjectIdAndMemberId(Long projectId, Long authorId);

    long countByParentId(Long parentId);

}
