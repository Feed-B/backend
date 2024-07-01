package com.example.team_12_be.project.rating.comment.service.dto;

import com.example.team_12_be.member.domain.Member;
import com.example.team_12_be.member.domain.vo.Job;
import com.example.team_12_be.project.rating.comment.domain.RatingReply;

public record ProjectCommentResponseDto(
        Long commentId,
        Long authorId,
        String authorName,
        Job job,
        String authorProfileImageUrl,
        String comment,
        float averageStarRank,
        Long childCommentCount
) {
    public static ProjectCommentResponseDto of(RatingReply ratingReply, Member commentAuthor, Long childCommentCount, float averageStarRank) {
        return new ProjectCommentResponseDto(
                ratingReply.getId(),
                commentAuthor.getId(),
                commentAuthor.getNickName(),
                commentAuthor.getMemberJob(),
                commentAuthor.getImageUrl(),
                ratingReply.getComment(),
                averageStarRank,
                childCommentCount
        );
    }
}
