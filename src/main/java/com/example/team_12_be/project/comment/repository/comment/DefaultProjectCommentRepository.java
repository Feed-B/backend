package com.example.team_12_be.project.comment.repository.comment;

import com.example.team_12_be.project.comment.domain.ProjectComment;
import com.example.team_12_be.project.comment.domain.ProjectCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DefaultProjectCommentRepository implements ProjectCommentRepository {

    private final ProjectCommentJpaRepository projectCommentJpaRepository;

    @Override
    public ProjectComment save(ProjectComment projectComment) {
        return projectCommentJpaRepository.save(projectComment);
    }

    @Override
    public Optional<ProjectComment> findById(Long id){
        return projectCommentJpaRepository.findById(id);
    }

    @Override
    public Slice<ProjectComment> findAllByProjectIdWithMember(Long projectId, Pageable pageable) {
        return projectCommentJpaRepository.findAllByProjectIdWithMember(projectId, pageable);
    }
    @Override
    public long countByParentCommentId(Long parentCommentId) {
        return projectCommentJpaRepository.countByParentId(parentCommentId);
    }

    @Override
    public Page<ProjectComment> findAllByParentCommentId(Long parentCommentId, Pageable pageable){
        return projectCommentJpaRepository.findAllByParentIdWithMember(parentCommentId, pageable);
    }

    @Override
    public Optional<ProjectComment> findByProjectIdAndMemberId(Long projectId, Long memberId) {
        return projectCommentJpaRepository.findByProjectIdAndMemberId(projectId, memberId);
    }

    @Override
    public Optional<ProjectComment> findByIdAndMemberId(Long id, Long memberId) {
        return projectCommentJpaRepository.findByIdAndMemberId(id, memberId);
    }

    @Override
    public List<ProjectComment> findAllByParentId(Long parentId) {
        return findAllByParentId(parentId);
    }

    @Override
    public void delete(ProjectComment projectComment) {
        projectCommentJpaRepository.delete(projectComment);
    }

    @Override
    public void deleteAll(List<ProjectComment> projectComments){
        projectCommentJpaRepository.deleteAll(projectComments);
    }
}