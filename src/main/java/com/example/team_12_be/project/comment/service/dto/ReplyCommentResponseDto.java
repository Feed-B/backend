package com.example.team_12_be.project.comment.service.dto;

import com.example.team_12_be.member.domain.vo.Job;
import com.example.team_12_be.project.comment.domain.ProjectComment;

public record ReplyCommentResponseDto(
        Long replyId,
        Long userId,
        Job job ,
        String author,
        String comment
) {
    public static ReplyCommentResponseDto of(ProjectComment projectComment){
        return new ReplyCommentResponseDto(
                projectComment.getId(),
                projectComment.getMember().getId(),
                projectComment.getMember().getMemberJob(),
                projectComment.getMember().getNickName(),
                projectComment.getComment()
        );
    }
}
