package com.example.team_12_be.project.rating.presentation;

import com.example.team_12_be.project.rating.service.ProjectRatingQueryService;
import com.example.team_12_be.project.rating.service.dto.response.ProjectRatingResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProjectRatingQueryController {

    private final ProjectRatingQueryService projectRatingQueryService;

    @GetMapping("/projects/{projectId}/ratings/{memberId}")
    @Operation(description = "프로젝트에 남긴 사용자의 별점 항목들 조회")
    public ProjectRatingResponseDto getMembersRating(@PathVariable Long projectId, @PathVariable Long memberId) {
        return projectRatingQueryService.getMemberProjectRating(memberId, projectId);
    }
}
