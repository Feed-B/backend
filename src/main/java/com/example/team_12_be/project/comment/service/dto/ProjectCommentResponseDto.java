package com.example.team_12_be.project.comment.service.dto;

import com.example.team_12_be.member.domain.Member;
import com.example.team_12_be.member.domain.vo.Job;
import com.example.team_12_be.project.comment.domain.ProjectComment;

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
    public static ProjectCommentResponseDto of(ProjectComment projectComment, Member commentAuthor, Long childCommentCount, float averageStarRank) {
        return new ProjectCommentResponseDto(
                projectComment.getId(),
                commentAuthor.getId(),
                commentAuthor.getNickName(),
                commentAuthor.getMemberJob(),
                commentAuthor.getImageUrl(),
                projectComment.getComment(),
                averageStarRank,
                childCommentCount
        );
    }
}
