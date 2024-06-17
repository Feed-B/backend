package com.example.team_12_be.project.presentation;

import com.example.team_12_be.project.service.ProjectService;
import com.example.team_12_be.project.service.dto.request.ProjectRatingRequestDto;
import com.example.team_12_be.project.service.dto.request.ProjectRequestDto;
import com.example.team_12_be.security.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Tag(name = "프로젝트 기능(C,U,D) 컨트롤러")
@SecurityRequirement(name = "Bearer Authentication")
@RestController
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("/projects")
    @Operation(description="프로젝트를 생성")
    public ResponseEntity<Void> saveProject(@RequestBody ProjectRequestDto projectRequestDto, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        Long projectId = projectService.saveProject(projectRequestDto, customUserDetails.getMember());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(projectId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/projects/{projectId}")
    @Operation(description="프로젝트를 삭제")
    public void deleteProject(@PathVariable Long projectId) {
        projectService.deleteProject(projectId);
    }

    @PostMapping("/projects/{projectId}/rank")
    @Operation(description="프로젝트에 대한 사용자의 별점 생성")
    public void addRating(@PathVariable Long projectId,
                          @AuthenticationPrincipal CustomUserDetails customUserDetails,
                          @RequestBody ProjectRatingRequestDto projectRatingRequestDto) {

        projectService.addRating(projectId, customUserDetails.getMember().getId(), projectRatingRequestDto);
    }

    @PostMapping("/projects/{projectId}/like")
    @Operation(description="프로젝트에 대한 사용자의 좋아요 생성")
    public void likeProject(@PathVariable Long projectId,
                            @AuthenticationPrincipal CustomUserDetails customUserDetails){

        Long memberId = customUserDetails.getMember().getId();
        projectService.likeProject(memberId, projectId);
    }

    @DeleteMapping("/projects/{projectId}/unlike")
    @Operation(description="프로젝트에 대한 사용자의 좋아요 삭제")
    public void unlikeProject(@PathVariable Long projectId,
                            @AuthenticationPrincipal CustomUserDetails customUserDetails){

        Long memberId = customUserDetails.getMember().getId();
        projectService.unlikeProject(memberId, projectId);
    }

    // TODO : 프로젝트 수정 API 추가
}