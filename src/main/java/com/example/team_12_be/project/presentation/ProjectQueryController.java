package com.example.team_12_be.project.presentation;

import com.example.team_12_be.global.page.CustomPageResponse;
import com.example.team_12_be.project.presentation.request.SortCondition;
import com.example.team_12_be.project.service.ProjectQueryService;
import com.example.team_12_be.project.service.dto.response.JobWithTeammateResponseDto;
import com.example.team_12_be.project.service.dto.response.LikedMembersTechStackResponseDto;
import com.example.team_12_be.project.service.dto.response.ProjectDetailForEditResponseDto;
import com.example.team_12_be.project.service.dto.response.ProjectDetailResponseDto;
import com.example.team_12_be.project.service.dto.response.ProjectListResponseDto;
import com.example.team_12_be.project.service.dto.response.ProjectRatingResponseDto;
import com.example.team_12_be.project.service.dto.response.StarRankResponseDto;
import com.example.team_12_be.security.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Tag(name = "프로젝트 GET(조회) 컨트롤러")
@SecurityRequirement(name = "Bearer Authentication")
@RestController
@RequiredArgsConstructor
public class ProjectQueryController {

    private final ProjectQueryService projectQueryService;

    @GetMapping("/projects/{projectId}")
    @Operation(description = "프로젝트 상세 조회")
    public ProjectDetailResponseDto getProjectDetail(@PathVariable Long projectId) {
        return projectQueryService.getDetailById(projectId);
    }

    @GetMapping("/projects/{projectId}/edits")
    @Operation(description = "프로젝트에 수정 페이지를 위한 데이터 조회")
    public ProjectDetailForEditResponseDto getDetailForEditByProjectId(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long projectId) {
        Long memberId = userDetails.getMember().getId();
        return projectQueryService.getDetailForEditByProjectId(memberId, projectId);
    }

    @GetMapping("/projects/{projectId}/teammates")
    @Operation(description = "프로젝트 팀원 목록 조회")
    public List<JobWithTeammateResponseDto> getTeammateListByProjectId(@PathVariable Long projectId) {
        return projectQueryService.getTeammateListByProjectId(projectId);
    }

    @GetMapping("/project/{projectId}/average-rating")
    @Operation(description = "프로젝트 별점 항목들 각각의 평균 조회")
    public StarRankResponseDto getProjectAverageStarRank(@PathVariable Long projectId) {
        return projectQueryService.getProjectAverageStarRankWithLikedInfo(projectId);
    }

    @GetMapping("/projects/{projectId}/ratings/{memberId}")
    @Operation(description = "프로젝트에 남긴 사용자의 별점 항목들 조회")
    public ProjectRatingResponseDto getMembersRating(@PathVariable Long projectId, @PathVariable Long memberId) {
        return projectQueryService.getMemberProjectRating(memberId, projectId);
    }

    @GetMapping("/project/{projectId}/likes")
    @Operation(description = "프로젝트에 좋아요 누른 횟수가 제일 많은 3개의 {job + 좋아요} 조회")
    public List<LikedMembersTechStackResponseDto> likedMembersTechStackList(@PathVariable Long projectId) {
        return projectQueryService.getLikedMembersTechStack(projectId);
    }

    @GetMapping("/projects")
    @Operation(description = "프로젝트 리스트 조회")
    public CustomPageResponse<ProjectListResponseDto> getProjectList(
            @RequestParam(name = "sortCondition") SortCondition sortCondition,
            @RequestParam(required = false) List<String> projectTechStacks, // TODO : 이거 enum 으로 교체
            @AuthenticationPrincipal Optional<CustomUserDetails> customUserDetailsOpt,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "100") int limit
    ) {
        Long memberId = null;

        if (!Objects.isNull(customUserDetailsOpt) && customUserDetailsOpt.isPresent()) {
            CustomUserDetails customUserDetails = customUserDetailsOpt.get();
            memberId = customUserDetails.getMember().getId();
        }

        if (page < 0){
            page = 0;
        }

        if (page > 0) {
            page -= 1;
        }

        int adjustedPageSize = Math.min(size, limit);
        Pageable pageable = PageRequest.of(page, adjustedPageSize);

        return projectQueryService.getProjectList(sortCondition, projectTechStacks, memberId, pageable);
    }
}
