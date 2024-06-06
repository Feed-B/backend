package com.example.team_12_be.project.service;

import com.example.team_12_be.member.domain.vo.TechStackValue;
import com.example.team_12_be.project.domain.Project;
import com.example.team_12_be.project.domain.ProjectLike;
import com.example.team_12_be.project.domain.ProjectQueryRepository;
import com.example.team_12_be.project.domain.ProjectRating;
import com.example.team_12_be.project.domain.vo.StarRank;
import com.example.team_12_be.project.service.dto.response.LikedMembersTechStackResponseDto;
import com.example.team_12_be.project.service.dto.response.ProjectDetailResponseDto;
import com.example.team_12_be.project.service.dto.response.ProjectRatingResponseDto;
import com.example.team_12_be.project.service.dto.response.StarRankResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectQueryService {

    private final ProjectQueryRepository projectQueryRepository;

    public Project findById(Long id) {
        return projectQueryRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Project with id " + id + " not found")
        );
    }

    public ProjectDetailResponseDto getDetailById(Long projectId) {
        Project project = projectQueryRepository.findById(projectId).orElseThrow(() -> new NoSuchElementException("Project with projectId " + projectId + " not found"));
        return ProjectDetailResponseDto.of(project);
    }

    public ProjectRatingResponseDto getMemberProjectRating(Long memberId, Long projectId) {
        ProjectRating projectRating = projectQueryRepository.findProjectRatingByMemberIdAndProjectId(memberId, projectId).orElseThrow(
                () -> new NoSuchElementException("Project with projectId " + projectId + " not found")
        );

        return ProjectRatingResponseDto.of(projectRating.getStarRank());
    }

    public StarRankResponseDto getProjectAverageStarRankWithLikedInfo(Long projectId) {
        StarRank projectAverageStarRank = this.getProjectAverageStarRank(projectId);
        List<LikedMembersTechStackResponseDto> likedMembersTechStack = this.getLikedMembersTechStack(projectId);

        return StarRankResponseDto.of(projectAverageStarRank, likedMembersTechStack);
    }

    public StarRank getProjectAverageStarRank(Long projectId){
        Project project = this.findById(projectId);

        return project.calculateAverageStarRank();
    }

    public List<LikedMembersTechStackResponseDto> getLikedMembersTechStack(Long projectId){
        List<ProjectLike> projectLikesWithMembers = projectQueryRepository.findLikesByProjectIdWithMember(projectId);
        List<TechStackValue> memberTechStacks = projectLikesWithMembers.stream()
                .map(each -> each.getMember().getMemberTechStack())
                .toList();

        return memberTechStacks.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue())) // 내림차순 정렬
                .limit(3) // 상위 3개만 선택
                .map(entry -> new LikedMembersTechStackResponseDto(entry.getKey(), entry.getValue()))
                .toList();
    }
}
