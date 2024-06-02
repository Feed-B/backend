package com.example.team_12_be.project.repository;

import com.example.team_12_be.project.domain.comment.ProjectComment;
import com.example.team_12_be.project.domain.comment.ProjectCommentRepository;
import lombok.RequiredArgsConstructor;
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
}