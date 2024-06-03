package com.example.team_12_be.project.service.dto.response;

import com.example.team_12_be.member.domain.vo.TechStackValue;
import com.example.team_12_be.project.domain.ProjectTechStack;

public record ProjectTechStackResponseDto (TechStackValue techStackValue) {

    public static ProjectTechStackResponseDto of(ProjectTechStack projectTechStack) {
        return new ProjectTechStackResponseDto(projectTechStack.getTechStack());
    }

}
