package com.example.team_12_be.project.comment.service.dto;

public record MyProjectCommentResponse(
        boolean exists,
        ProjectCommentResponseDto projectCommentResponseDto
) {
}
