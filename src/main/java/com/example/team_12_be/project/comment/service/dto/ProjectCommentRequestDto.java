package com.example.team_12_be.project.comment.service.dto;

import com.example.team_12_be.member.domain.Member;
import com.example.team_12_be.project.comment.domain.ProjectComment;
import com.example.team_12_be.project.domain.Project;
import jakarta.validation.constraints.NotNull;

public record ProjectCommentRequestDto(
        Long parentId,

        @NotNull
        String comment
) {
        public ProjectComment toEntity(Project project, Member member){
                if (parentId == 0){
                        return new ProjectComment(null, comment, project, member);
                }
                return new ProjectComment(parentId, comment, project, member);
        }
}