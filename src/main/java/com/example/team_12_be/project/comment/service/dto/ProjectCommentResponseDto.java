package com.example.team_12_be.project.comment.service.dto;

import com.example.team_12_be.project.comment.domain.ProjectComment;

public record ProjectCommentResponseDto(
        String comment
) {
    // TODO : 온전한 데이터를 완성한다
    public static ProjectCommentResponseDto of(ProjectComment projectComment) {
        return new ProjectCommentResponseDto(
                projectComment.getComment()
        );
    }
}
