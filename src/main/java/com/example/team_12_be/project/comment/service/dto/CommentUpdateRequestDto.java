package com.example.team_12_be.project.comment.service.dto;

import com.example.team_12_be.project.service.dto.request.ProjectRatingUpdateRequestDto;

public record CommentUpdateRequestDto(
        String comment,
        ProjectRatingUpdateRequestDto projectRatingUpdateRequest
) {
}
