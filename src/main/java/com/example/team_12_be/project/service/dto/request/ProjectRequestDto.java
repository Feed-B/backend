package com.example.team_12_be.project.service.dto.request;

import com.example.team_12_be.member.domain.Member;
import com.example.team_12_be.member.domain.vo.Job;
import com.example.team_12_be.project.domain.Project;
import com.example.team_12_be.project.domain.ProjectTeammate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

// TODO : 메시지 정리, constraint 정리
public record ProjectRequestDto(
        @NotBlank
        String title,
        @NotBlank
        String introduction,
        String content,
        @NotBlank
        String serviceUrl,
        @NotEmpty
        List<String> projectTechStacks,
//        Long thumbnailImageId,
//        List<PrjectImage> projectImages,
//        List<ProjectComment> projectComments,
//        List<ProjectLike> projectLikes,
        @NotEmpty
        List<ProjectTeammateRequestDto> projectTeammates,
        @NotEmpty
        List<ProjectLinkRequestDto> projectLinks
) {
    public Project toEntity(Member author) {
        return new Project(
                title,
                introduction,
                content,
                author,
                serviceUrl
        );
    }
}