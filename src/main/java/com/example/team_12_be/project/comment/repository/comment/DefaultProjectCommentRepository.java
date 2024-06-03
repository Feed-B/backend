package com.example.team_12_be.project.comment.repository.comment;

import com.example.team_12_be.project.comment.domain.ProjectComment;
import com.example.team_12_be.project.comment.domain.ProjectCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

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
    public Slice<ProjectComment> findAllByProjectId(Long projectId, Pageable pageable) {
        return projectCommentJpaRepository.findAllByProjectId(projectId, pageable);
    }
}