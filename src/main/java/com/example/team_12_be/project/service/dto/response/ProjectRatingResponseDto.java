package com.example.team_12_be.project.service.dto.response;

import com.example.team_12_be.project.domain.vo.StarRank;

public record ProjectRatingResponseDto(float averageRank,
                                       float ideaRank,
                                       float designRank,
                                       float functionRank,
                                       float completionRank) {
    public static ProjectRatingResponseDto of(StarRank starRank) {
        return new ProjectRatingResponseDto(starRank.getAverageRank(),
                starRank.getIdeaRank(),
                starRank.getDesignRank(),
                starRank.getFunctionRank(),
                starRank.getCompletionRank()
        );
    }
}
