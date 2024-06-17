package com.example.team_12_be.project.presentation;

import com.example.team_12_be.project.service.ProjectQueryService;
import com.example.team_12_be.project.service.dto.response.JobWithTeammateResponseDto;
import com.example.team_12_be.project.service.dto.response.LikedMembersTechStackResponseDto;
import com.example.team_12_be.project.service.dto.response.ProjectDetailForEditResponseDto;
import com.example.team_12_be.project.service.dto.response.ProjectDetailResponseDto;
import com.example.team_12_be.project.service.dto.response.ProjectRatingResponseDto;
import com.example.team_12_be.project.service.dto.response.StarRankResponseDto;
import com.example.team_12_be.security.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "프로젝트 GET(조회) 컨트롤러")
@SecurityRequirement(name = "Bearer Authentication")
@RestController
@RequiredArgsConstructor
public class ProjectQueryController {

    private final ProjectQueryService projectQueryService;

    @GetMapping("/projects/{projectId}")
    @Operation(description="프로젝트 상세 조회")
    public ProjectDetailResponseDto getProjectDetail(@PathVariable  Long projectId) {
        return projectQueryService.getDetailById(projectId);
    }

    @GetMapping("/projects/{projectId}/edits")
    @Operation(description="프로젝트에 수정 페이지를 위한 데이터 조회")
    public ProjectDetailForEditResponseDto getDetailForEditByProjectId(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long projectId){
        Long memberId = userDetails.getMember().getId();
        return projectQueryService.getDetailForEditByProjectId(memberId, projectId);
    }

    @GetMapping("/projects/{projectId}/teammates")
    @Operation(description="프로젝트 팀원 목록 조회")
    public List<JobWithTeammateResponseDto> getTeammateListByProjectId(@PathVariable Long projectId){
        return projectQueryService.getTeammateListByProjectId(projectId);
    }
    @GetMapping("/project/{projectId}/average-rating")
    @Operation(description="프로젝트 별점 항목들 각각의 평균 조회")
    public StarRankResponseDto getProjectAverageStarRank(@PathVariable Long projectId) {
        return projectQueryService.getProjectAverageStarRankWithLikedInfo(projectId);
    }

    @GetMapping("/projects/{projectId}/ratings/{memberId}")
    @Operation(description="프로젝트에 남긴 사용자의 별점 항목들 조회")
    public ProjectRatingResponseDto getMembersRating(@PathVariable Long projectId, @PathVariable Long memberId) {
        return projectQueryService.getMemberProjectRating(memberId, projectId);
    }

    @GetMapping("/project/{projectId}/likes")
    @Operation(description="프로젝트에 좋아요 누른 횟수가 제일 많은 3개의 {job + 좋아요} 조회")
    public List<LikedMembersTechStackResponseDto> likedMembersTechStackList(@PathVariable Long projectId){
        return projectQueryService.getLikedMembersTechStack(projectId);
    }
    // TODO : 리스트 조회 응답 시에도 좋아요 여부를 같이 태워 보내준다.
    // TODO : 리스트 조회 API
//    @GetMapping("/projects")
//    public void foo(
//            @RequestParam(required = false) boolean latest,
//            @RequestParam(required = false) boolean viewCount,
//            @RequestParam(required = false) boolean likeCount,
//            @RequestParam(required = false) List<String> projectTechStacks // TODO : 이거 enum 으로 교체
//    ){
//        projectQueryService.
//    }
}
