package com.example.team_12_be.post.presentation;

import com.example.team_12_be.member.domain.Member;
import com.example.team_12_be.post.application.ProjectService;
import com.example.team_12_be.post.application.dto.ProjectRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    // TODO : Authentication 완료되면 작성 유저의 정보도 받아야 한다.

    @PostMapping("/projects")
    public void saveProject(@RequestBody ProjectRequestDto projectRequestDto, @AuthenticationPrincipal Member member) {
        projectService.saveProject(projectRequestDto, member);
    }

    @DeleteMapping("/projects/{projectId}")
    public void deleteProject(@PathVariable Long projectId) {
        projectService.deleteProject(projectId);
    }
}