package com.example.team_12_be.project.service.dto.response;

import com.example.team_12_be.project.domain.ProjectTeammate;

public record ProjectTeammateResponseDto(
        String teammateName
) {
    public static ProjectTeammateResponseDto of(ProjectTeammate projectTeammate) {
        return new ProjectTeammateResponseDto(projectTeammate.getTeammateName());
    }
}
