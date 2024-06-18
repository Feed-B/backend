package com.example.team_12_be.project.service.dto.response;

import com.example.team_12_be.project.domain.Project;
import com.example.team_12_be.project.domain.ProjectImage;

import java.time.LocalDateTime;
import java.util.List;

public record ProjectDetailResponseDto(
        // 프로젝트 정보, 댓글 8개(최대)
        Long projectId,
        Long memberId,
        String authorName,
        LocalDateTime createdAt,
        Long likeCount,
        String title,
        String content,
        String introductions,
        String serviceUrl,
        List<ProjectLinkResponseDto> projectLinks,
        List<ProjectTechStackResponseDto> projectTechStacks,
        String thumbnailUrl,
        List<String> imageUrlList,
        boolean isMine
) {
    public static ProjectDetailResponseDto of(Project project, Long likeCount, boolean isMine) {
        return new ProjectDetailResponseDto(
                project.getId(),
                project.getAuthor().getId(),
                project.getAuthor().getNickName(),
                project.getCreatedAt(),
                likeCount,
                project.getTitle(),
                project.getIntroductions(),
                project.getContent(),
                project.getServiceUrl(),
                project.getProjectLinks().stream().map(ProjectLinkResponseDto::of).toList(),
                project.getProjectTechStacks().stream().map(ProjectTechStackResponseDto::of).toList(),
                project.getThumbnailUrl(),
                project.getProjectImages().stream().map(ProjectImage::getUrl).toList(),
                isMine
        );
    }
}