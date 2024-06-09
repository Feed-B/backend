package com.example.team_12_be.project.presentation;

import com.example.team_12_be.project.service.ProjectQueryService;
import com.example.team_12_be.project.service.dto.response.JobWithTeammateResponseDto;
import com.example.team_12_be.project.service.dto.response.LikedMembersTechStackResponseDto;
import com.example.team_12_be.project.service.dto.response.ProjectDetailResponseDto;
import com.example.team_12_be.project.service.dto.response.ProjectRatingResponseDto;
import com.example.team_12_be.project.service.dto.response.StarRankResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProjectQueryController {

    private final ProjectQueryService projectQueryService;

    @GetMapping("/projects")
    public ProjectDetailResponseDto getProjectDetail(@RequestParam Long projectId) {
        return projectQueryService.getDetailById(projectId);
    }

    // TODO : 리스트 조회 응답 시에도 좋아요 여부를 같이 태워 보내준다.
    // TODO : 리스트 조회 API

    @GetMapping("/projects/{projectId}/teammates")
    public List<JobWithTeammateResponseDto> getTeammateListByProjectId(@PathVariable Long projectId){
        return projectQueryService.getTeammateListByProjectId(projectId);
    }
    // TODO : Auth 완성되면 다시
    @GetMapping("/projects/{projectId}/ratings/member")
    public ProjectRatingResponseDto getMembersRating(@PathVariable Long projectId, @RequestParam Long memberId) {
        return projectQueryService.getMemberProjectRating(memberId, projectId);
    }

    // TODO : 좋아요 리스트 넘겨 줄 때, 테크스택:갯수 데이터를 분리 할 수 있다.
    // TODO : 갯수를 추가해서 (리뷰 갯수)
    @GetMapping("/project/{projectId}/average-rating")
    public StarRankResponseDto getProjectAverageStarRank(@PathVariable Long projectId) {
        return projectQueryService.getProjectAverageStarRankWithLikedInfo(projectId);
    }

    @GetMapping("/project/{projectId}/likes/average")
    public List<LikedMembersTechStackResponseDto> likedMembersTechStackList(@PathVariable Long projectId){
        return projectQueryService.getLikedMembersTechStack(projectId);
    }
}
