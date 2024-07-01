package com.example.team_12_be.project.rating.comment.service.dto;

import com.example.team_12_be.project.rating.service.dto.request.ProjectRatingUpdateRequestDto;

public record CommentUpdateRequestDto(
        String comment,
        ProjectRatingUpdateRequestDto projectRatingUpdateRequest
) {
}
