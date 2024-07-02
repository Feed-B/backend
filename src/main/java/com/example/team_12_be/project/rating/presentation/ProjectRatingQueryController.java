package com.example.team_12_be.project.rating.presentation;

import com.example.team_12_be.project.rating.service.ProjectRatingQueryService;
import com.example.team_12_be.project.rating.service.dto.response.MyProjectRatingResponseDto;
import com.example.team_12_be.project.rating.service.dto.response.ProjectRatingResponseDto;
import com.example.team_12_be.security.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "프로젝트 별점 조회 컨트롤러")
@SecurityRequirement(name = "Bearer Authentication")
@RestController
@RequiredArgsConstructor
public class ProjectRatingQueryController {

    private final ProjectRatingQueryService projectRatingQueryService;

    @GetMapping("/projects/{projectId}/ratings/{memberId}")
    @Operation(description = "프로젝트에 남긴 사용자의 별점 항목들 조회")
    public ProjectRatingResponseDto getMembersRating(@PathVariable Long projectId, @PathVariable Long memberId) {
        return projectRatingQueryService.getMemberProjectRating(memberId, projectId);
    }
    @GetMapping("/projects/{projectId}/ratings/mine")
    @Operation(description = "프로젝트에 달린 나의 별점 조회 - (exists 로 존재여부 판별)")
    public MyProjectRatingResponseDto getMyComment(@PathVariable Long projectId,
                                                   @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return projectRatingQueryService.getMyProjectRating(projectId, customUserDetails.getMember().getId());
    }
}
