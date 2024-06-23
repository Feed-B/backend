package com.example.team_12_be.project.comment.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;

public interface ProjectCommentRepository {

    ProjectComment save(ProjectComment projectComment);

    Optional<ProjectComment> findById(Long id);

    // 혹은 pageAble
    Slice<ProjectComment> findAllByProjectIdWithMember(Long projectId, Pageable pageable);

    long countByParentCommentId(Long parentCommentId);

    Page<ProjectComment> findAllByParentCommentId(Long parentCommentId, Pageable pageable);

    Optional<ProjectComment> findByProjectIdAndMemberId(Long projectId, Long memberId);

    Optional<ProjectComment> findByIdAndMemberId(Long id, Long memberId);

    List<ProjectComment> findAllByParentId(Long parentId);

    void delete(ProjectComment projectComment);

    void deleteAll(List<ProjectComment> projectComments);
}
