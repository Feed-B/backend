package com.example.team_12_be.project.presentation;

import com.example.team_12_be.project.service.ProjectQueryService;
import com.example.team_12_be.project.service.dto.response.ProjectDetailResponseDto;
import com.example.team_12_be.project.service.dto.response.ProjectRatingResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    // TODO : Auth 완성되면 다시
    @GetMapping("/projects/{projectId}/rank")
    public ProjectRatingResponseDto getProjectRating(@PathVariable Long projectId, @RequestParam Long memberId) {
        return projectQueryService.getMemberProjectRating(memberId, projectId);
    }
}
