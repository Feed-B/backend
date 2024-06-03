package com.example.team_12_be.project.comment.service;

import com.example.team_12_be.global.page.CustomPageResponse;
import com.example.team_12_be.project.comment.service.dto.ProjectCommentResponseDto;
import com.example.team_12_be.project.comment.domain.ProjectComment;
import com.example.team_12_be.project.comment.domain.ProjectCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectCommentQueryService {

    private final ProjectCommentRepository projectCommentRepository;

    public CustomPageResponse<ProjectCommentResponseDto> findProjectCommentsByProjectId(Long projectId, Pageable pageable) {
        Slice<ProjectComment> projectCommentsPage = projectCommentRepository.findAllByProjectId(projectId, pageable);
        List<ProjectCommentResponseDto> projectCommentResponses = projectCommentsPage.stream().map(ProjectCommentResponseDto::of).toList();

        return new CustomPageResponse<>(projectCommentResponses, pageable, projectCommentsPage.hasNext());
    }
}