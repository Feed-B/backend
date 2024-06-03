package com.example.team_12_be.project.presentation;

import com.example.team_12_be.project.service.ProjectQueryService;
import com.example.team_12_be.project.service.dto.response.ProjectDetailResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProjectQueryController {

    private final ProjectQueryService projectQueryService;

    @GetMapping("/projects")
    public ProjectDetailResponseDto getProjectDetail(@RequestParam Long projectId) {
        return projectQueryService.getDetailById(projectId);
    }
}
