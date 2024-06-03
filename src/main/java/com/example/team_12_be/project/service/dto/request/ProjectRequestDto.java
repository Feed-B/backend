package com.example.team_12_be.project.service.dto.request;

import com.example.team_12_be.member.domain.Member;
import com.example.team_12_be.project.domain.Project;
import com.example.team_12_be.project.domain.ProjectLink;
import com.example.team_12_be.project.domain.ProjectTeammate;
import com.example.team_12_be.project.domain.ProjectTechStack;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

// TODO : 메시지 정리, constraint 정리
public record ProjectRequestDto(
        @NotBlank
        String title,
        @NotBlank
        String introductions,
        @NotBlank
        String serviceUrl,
        @NotEmpty
        List<ProjectTechStack> projectTechStacks,
//        Long thumbnailImageId,
//        List<PrjectImage> projectImages,
//        List<ProjectComment> projectComments,
//        List<ProjectLike> projectLikes,
        @NotEmpty
        List<ProjectTeammate> projectTeammates,
        @NotEmpty
        List<ProjectLink> projectLinks
) {
    public Project toEntity(Member author) {
        return new Project(
                this.title,
                this.introductions,
                author,
                serviceUrl,
                projectTechStacks,
                projectTeammates,
                projectLinks
        );
    }
}