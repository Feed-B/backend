package com.example.team_12_be.project.comment.service;

import com.example.team_12_be.global.page.CustomPageResponse;
import com.example.team_12_be.member.domain.Member;
import com.example.team_12_be.project.comment.domain.ProjectComment;
import com.example.team_12_be.project.comment.domain.ProjectCommentRepository;
import com.example.team_12_be.project.comment.service.dto.ProjectCommentResponseDto;
import com.example.team_12_be.project.domain.ProjectRating;
import com.example.team_12_be.project.repository.ProjectRatingJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectCommentQueryService {

    private final ProjectCommentRepository projectCommentRepository;

    private final ProjectRatingJpaRepository projectRatingJpaRepository;

    public CustomPageResponse<ProjectCommentResponseDto> findProjectCommentsByProjectId(Long projectId, Pageable pageable) {
        Slice<ProjectComment> projectCommentsPage = projectCommentRepository.findAllByProjectIdWithMember(projectId, pageable);
        List<ProjectCommentResponseDto> projectCommentResponses = projectCommentsPage.stream()
                .map(each -> this.getProjectCommentResponseDto(projectId, each))
                .toList();

        return new CustomPageResponse<>(projectCommentResponses, pageable, projectCommentsPage.hasNext());
    }

    // TODO : 대댓글 조회 API 작성

    private ProjectCommentResponseDto getProjectCommentResponseDto(Long projectId, ProjectComment projectComment) {
        long childCommentCount = projectCommentRepository.countByParentCommentId(projectComment.getParentId());
        Member commentAuthor = projectComment.getMember();

        ProjectRating projectRating = projectRatingJpaRepository.findByMemberIdAndProjectId(commentAuthor.getId(), projectId).orElseThrow(
                () -> new IllegalArgumentException("평가하지 않은 유저")
        );
        float averageRank = projectRating.getStarRank().getAverageRank();

        return ProjectCommentResponseDto.of(projectComment, commentAuthor, childCommentCount, averageRank);
    }
}