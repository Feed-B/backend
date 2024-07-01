package com.example.team_12_be.project.rating.service.dto.response;

import com.example.team_12_be.project.domain.ProjectRating;
import com.example.team_12_be.project.domain.vo.StarRank;

public record ProjectRatingResponseDto(float averageRank,
                                       float ideaRank,
                                       float designRank,
                                       float functionRank,
                                       float completionRank,
                                       String comment) {
    public static ProjectRatingResponseDto of(ProjectRating projectRating) {
        StarRank starRank = projectRating.getStarRank();
        return new ProjectRatingResponseDto(starRank.getAverageRank(),
                starRank.getIdeaRank(),
                starRank.getDesignRank(),
                starRank.getFunctionRank(),
                starRank.getCompletionRank(),
                projectRating.getComment()
        );
    }
}
