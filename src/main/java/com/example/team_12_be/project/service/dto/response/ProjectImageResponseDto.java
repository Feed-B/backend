package com.example.team_12_be.project.service.dto.response;

import com.example.team_12_be.project.domain.ProjectImage;

public record ProjectImageResponseDto(
        Long id,
        String url
) {
    public static ProjectImageResponseDto of(ProjectImage projectImage){
        return new ProjectImageResponseDto(
                projectImage.getId(),
                projectImage.getUrl()
        );
    }
}
