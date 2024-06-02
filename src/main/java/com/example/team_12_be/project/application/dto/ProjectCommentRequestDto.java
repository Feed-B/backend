package com.example.team_12_be.project.application.dto;

import com.example.team_12_be.global.validate.HalfOrWholeNumber;
import com.example.team_12_be.member.domain.Member;
import com.example.team_12_be.project.domain.Project;
import com.example.team_12_be.project.domain.comment.ProjectComment;
import com.example.team_12_be.project.domain.comment.vo.StarRank;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ProjectCommentRequestDto(
        Long parentId,

        @NotNull
        String comment,

        @NotNull @Min(0) @Max(5) @HalfOrWholeNumber
        float ideaRank,

        @NotNull @Min(0) @Max(5) @HalfOrWholeNumber
        float designRank,

        @NotNull @Min(0) @Max(5) @HalfOrWholeNumber
        float functionRank,

        @NotNull @Min(0) @Max(5) @HalfOrWholeNumber
        float completionRank
) {
        public ProjectComment toEntity(Project project, Member member){
                return new ProjectComment(
                        parentId,
                        comment,
                        new StarRank(
                                ideaRank,
                                designRank,
                                functionRank,
                                completionRank),
                        project,
                        member
                );

        }
}