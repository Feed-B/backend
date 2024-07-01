package com.example.team_12_be.project.rating.comment.service;

import com.example.team_12_be.global.page.CustomPageResponse;
import com.example.team_12_be.member.domain.Member;
import com.example.team_12_be.project.domain.ProjectRating;
import com.example.team_12_be.project.rating.comment.domain.ProjectCommentRepository;
import com.example.team_12_be.project.rating.comment.domain.RatingReply;
import com.example.team_12_be.project.rating.comment.service.dto.ProjectCommentResponseDto;
import com.example.team_12_be.project.rating.comment.service.dto.ReplyCommentResponseDto;
import com.example.team_12_be.project.rating.repository.ProjectRatingJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectCommentQueryService {

    private final ProjectCommentRepository projectCommentRepository;

    private final ProjectRatingJpaRepository projectRatingJpaRepository;

    public CustomPageResponse<ProjectCommentResponseDto> findProjectCommentsByProjectId(Long projectId, Pageable pageable) {
        Slice<RatingReply> projectCommentsPage = projectCommentRepository.findAllByProjectIdWithMember(projectId, pageable);
        List<ProjectCommentResponseDto> projectCommentResponses = projectCommentsPage.stream()
                .map(each -> this.getProjectCommentResponseDto(projectId, each))
                .toList();

        return new CustomPageResponse<>(projectCommentResponses, pageable, projectCommentsPage.getNumberOfElements());
    }

    public CustomPageResponse<ReplyCommentResponseDto> findAllReplyByParentCommentId(Long parentCommentId, Pageable pageable) {

        Page<RatingReply> replyCommentList = projectCommentRepository.findAllByParentCommentId(parentCommentId, pageable);
        List<ReplyCommentResponseDto> replyCommentResponseDtoList = replyCommentList.stream()
                .map(ReplyCommentResponseDto::of)
                .toList();

        return new CustomPageResponse<>(replyCommentResponseDtoList, pageable, replyCommentList.getTotalElements());
    }

    public ProjectCommentResponseDto getProjectCommentResponseDto(Long projectId, Long commentId) {
        RatingReply ratingReply = projectCommentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 댓글")
        );

        return getProjectCommentResponseDto(projectId, ratingReply);
    }

    public ProjectCommentResponseDto getMyProjectComment(Long ratingId, Long memberId) {
        Optional<RatingReply> projectCommentOpt = projectCommentRepository.findByProjectIdAndMemberId(ratingId, memberId);

        if (projectCommentOpt.isEmpty()) {
            return null;
        }

        RatingReply ratingReply = projectCommentOpt.get();
        return getProjectCommentResponseDto(ratingId, ratingReply);
    }

    private ProjectCommentResponseDto getProjectCommentResponseDto(Long projectId, RatingReply ratingReply) {
        long childCommentCount = projectCommentRepository.countByParentCommentId(ratingReply.getParentId());
        Member commentAuthor = ratingReply.getMember();

        ProjectRating projectRating = projectRatingJpaRepository.findByMemberIdAndProjectId(commentAuthor.getId(), projectId).orElseThrow(
                () -> new IllegalArgumentException("평가하지 않은 유저")
        );
        float averageRank = projectRating.getStarRank().getAverageRank();

        return ProjectCommentResponseDto.of(ratingReply, commentAuthor, childCommentCount, averageRank);
    }
}