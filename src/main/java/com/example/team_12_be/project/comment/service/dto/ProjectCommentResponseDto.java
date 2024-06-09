package com.example.team_12_be.project.comment.service.dto;

import com.example.team_12_be.member.domain.Member;
import com.example.team_12_be.member.domain.vo.Job;
import com.example.team_12_be.project.comment.domain.ProjectComment;

public record ProjectCommentResponseDto(
        Long commentId,
        String author,
        Long childCommentCount,
        float averageStarRank,
        String comment,
        Job job
) {
    public static ProjectCommentResponseDto of(ProjectComment projectComment, Member commentAuthor, Long childCommentCount, float starRank) {
    // TODO : 온전한 데이터를 완성한다
        return new ProjectCommentResponseDto(
                projectComment.getId(),
                commentAuthor.getNickName(),
                childCommentCount,
                starRank,
                projectComment.getComment(),
                commentAuthor.getMemberTechStack()
        );
    }
}
