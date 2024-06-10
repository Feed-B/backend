package com.example.team_12_be.project.presentation;

import com.example.team_12_be.project.service.ProjectQueryService;
import com.example.team_12_be.project.service.dto.response.*;
import com.example.team_12_be.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProjectQueryController {
    // TODO : Auth 완성되면 다시
    private final ProjectQueryService projectQueryService;

    // TODO : 리스트 조회 응답 시에도 좋아요 여부를 같이 태워 보내준다.
    // TODO : 리스트 조회 API

    @GetMapping("/projects/{projectId}")
    public ProjectDetailResponseDto getProjectDetail(@PathVariable Long projectId) {
        return projectQueryService.getDetailById(projectId);
    }

    @GetMapping("/projects/{projectId}/edits")
    public ProjectDetailForEditResponseDto getDetailForEditByProjectId(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long projectId){
        Long memberId = userDetails.getMember().getId();
        return projectQueryService.getDetailForEditByProjectId(memberId, projectId);
    }

    @GetMapping("/projects/{projectId}/teammates")
    public List<JobWithTeammateResponseDto> getTeammateListByProjectId(@PathVariable Long projectId){
        return projectQueryService.getTeammateListByProjectId(projectId);
    }
    @GetMapping("/project/{projectId}/average-rating")
    public StarRankResponseDto getProjectAverageStarRank(@PathVariable Long projectId) {
        return projectQueryService.getProjectAverageStarRankWithLikedInfo(projectId);
    }

    @GetMapping("/projects/{projectId}/ratings/{memberId}")
    public ProjectRatingResponseDto getMembersRating(@PathVariable Long projectId, @PathVariable Long memberId) {
        return projectQueryService.getMemberProjectRating(memberId, projectId);
    }

    @GetMapping("/project/{projectId}/likes/average")
    public List<LikedMembersTechStackResponseDto> likedMembersTechStackList(@PathVariable Long projectId){
        return projectQueryService.getLikedMembersTechStack(projectId);
    }
}
