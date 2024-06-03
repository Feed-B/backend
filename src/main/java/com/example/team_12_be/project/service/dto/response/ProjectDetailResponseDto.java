package com.example.team_12_be.project.service.dto.response;

import com.example.team_12_be.project.domain.Project;

import java.util.List;

public record ProjectDetailResponseDto(
        // 프로젝트 정보, 댓글 8개(최대)
        Long id,
        String title,
        String introductions,
        Long memberId,
        String serviceUrl,
        List<ProjectTechStackResponseDto> projectTechStackResponseDtoList,
        List<ProjectTeammateResponseDto> projectTeammateResponseDtoList,
        List<ProjectLinkResponseDto> projectLinkResponseDtoList
//        List<ProjectCommentResponseDto> projectCommentResponseDtoList
) {
    // TODO : 온전한 데이터를 완성한다.
    public static ProjectDetailResponseDto of(Project project) {
        return new ProjectDetailResponseDto(
                project.getId(),
                project.getTitle(),
                project.getIntroductions(),
                project.getAuthor().getId(),
                project.getServiceUrl(),
                project.getProjectTechStacks().stream().map(ProjectTechStackResponseDto::of).toList(),
                project.getProjectTeammates().stream().map(ProjectTeammateResponseDto::of).toList(),
                project.getProjectLinks().stream().map(ProjectLinkResponseDto::of).toList()
//                project.getProjectComments().stream().map(ProjectCommentResponseDto::of).toList()
        );
    }
}