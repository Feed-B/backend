package com.example.team_12_be.project.service.dto.response;

import com.example.team_12_be.project.domain.vo.StarRank;

import java.util.List;

public record StarRankResponseDto(float averageRank,
                                  float ideaRank,
                                  float designRank,
                                  float functionRank,
                                  float completionRank,
                                  List<LikedMembersTechStackResponseDto> likedStacks) {

    public static StarRankResponseDto of(StarRank rank, List<LikedMembersTechStackResponseDto> likedStacks) {
        return new StarRankResponseDto(
                rank.getAverageRank(),
                rank.getIdeaRank(),
                rank.getDesignRank(),
                rank.getFunctionRank(),
                rank.getCompletionRank(),
                likedStacks
        );
    }
}
