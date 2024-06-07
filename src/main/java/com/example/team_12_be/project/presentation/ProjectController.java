package com.example.team_12_be.project.presentation;

import com.example.team_12_be.project.service.ProjectService;
import com.example.team_12_be.project.service.dto.request.ProjectRatingRequestDto;
import com.example.team_12_be.project.service.dto.request.ProjectRequestDto;
import com.example.team_12_be.security.CustomUserDetails;
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
    public void saveProject(@RequestBody ProjectRequestDto projectRequestDto, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        projectService.saveProject(projectRequestDto, customUserDetails.getMember());
    }

    @DeleteMapping("/projects/{projectId}")
    public void deleteProject(@PathVariable Long projectId) {
        projectService.deleteProject(projectId);
    }

    @PostMapping("/projects/{projectId}/rank")
    public void addRating(@PathVariable Long projectId,
                          @AuthenticationPrincipal CustomUserDetails customUserDetails,
                          @RequestBody ProjectRatingRequestDto projectRatingRequestDto) {

        projectService.addRating(projectId, customUserDetails.getMember().getId(), projectRatingRequestDto);
    }

    @PostMapping("/projects/{projectId}/like")
    public void likeProject(@PathVariable Long projectId,
                            @AuthenticationPrincipal CustomUserDetails customUserDetails){

        Long memberId = customUserDetails.getMember().getId();
        projectService.likeProject(memberId, projectId);
    }

    @DeleteMapping("/projects/{projectId}/unlike")
    public void unlikeProject(@PathVariable Long projectId,
                            @AuthenticationPrincipal CustomUserDetails customUserDetails){

        Long memberId = customUserDetails.getMember().getId();
        projectService.unlikeProject(memberId, projectId);
    }
}