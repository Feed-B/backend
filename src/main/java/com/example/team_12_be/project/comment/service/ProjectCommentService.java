package com.example.team_12_be.project.comment.service;

import com.example.team_12_be.member.service.dto.MemberService;
import com.example.team_12_be.member.domain.Member;
import com.example.team_12_be.project.comment.service.dto.CommentUpdateRequestDto;
import com.example.team_12_be.project.service.ProjectQueryService;
import com.example.team_12_be.project.comment.service.dto.ProjectCommentRequestDto;
import com.example.team_12_be.project.domain.Project;
import com.example.team_12_be.project.comment.domain.ProjectComment;
import com.example.team_12_be.project.comment.domain.ProjectCommentRepository;
import com.example.team_12_be.project.service.ProjectRatingService;
import com.example.team_12_be.project.service.dto.request.ProjectRatingRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectCommentService {

    private final ProjectCommentRepository projectCommentRepository;

    private final ProjectQueryService projectQueryService;

    private final MemberService memberService;

    private final ProjectRatingService projectRatingService;

    public Long addCommentAndRating(Long projectId, Long memberId, ProjectRatingRequestDto projectRatingRequestDto) {
        Project commnetedProject = projectQueryService.findById(projectId);
        Member commentAuthor = memberService.findById(memberId);
        projectRatingService.addRating(commnetedProject, commentAuthor, projectRatingRequestDto);

        ProjectComment newComment = addComment(commnetedProject, commentAuthor, projectRatingRequestDto.comment());
        return newComment.getId();
    }

    private ProjectComment addComment(Project commentedProject, Member commentAuthor, String comment) {
        ProjectComment newComment = new ProjectComment(null, comment, commentedProject, commentAuthor);
        projectCommentRepository.save(newComment);

//        this.assignParentId(projectCommentRequestDto, newComment);

        return newComment;
    }

    public Long addReply(Long projectId, Long memberId, ProjectCommentRequestDto projectCommentRequestDto){
        Project commnetedProject = projectQueryService.findById(projectId);
        Member commentAuthor = memberService.findById(memberId);

        ProjectComment newComment = this.addComment(commnetedProject, commentAuthor, projectCommentRequestDto.comment());
        this.assignParentId(projectCommentRequestDto, newComment);
        return newComment.getId();
    }

    public void updateComment(Long commentId, Long memberId, CommentUpdateRequestDto commentUpdateRequestDto) {
        ProjectComment projectComment = projectCommentRepository.findByIdAndMemberId(commentId, memberId).orElseThrow(
                () -> new IllegalArgumentException("없는 코멘트")
        );

        projectComment.updateContent(commentUpdateRequestDto.comment());
        projectRatingService.modifyRating(memberId, commentUpdateRequestDto.projectRatingUpdateRequest());
    }

    public void updateReply(Long commentId, Long memberId, ProjectCommentRequestDto projectCommentRequestDto){
        ProjectComment projectComment = projectCommentRepository.findByIdAndMemberId(commentId, memberId).orElseThrow(
                () -> new IllegalArgumentException("없는 코멘트")
        );

        projectComment.updateContent(projectCommentRequestDto.comment());
    }

    public void deleteComment(Long commentId, Long memberId) {
        ProjectComment projectComment = projectCommentRepository.findByIdAndMemberId(commentId, memberId).orElseThrow(
                () -> new IllegalArgumentException("없는 코멘트")
        );

        Project commnetedProject = projectQueryService.findById(projectComment.getProject().getId());

        deleteChildIfPresent(projectComment);
        projectRatingService.deleteRating(memberId, commnetedProject);
        projectCommentRepository.delete(projectComment);
    }

    public void deleteReply(Long commentId, Long memberId){
        ProjectComment projectComment = projectCommentRepository.findByIdAndMemberId(commentId, memberId).orElseThrow(
                () -> new IllegalArgumentException("없는 코멘트")
        );

        projectCommentRepository.delete(projectComment);
    }

    private void deleteChildIfPresent(ProjectComment projectComment) {
        List<ProjectComment> childComments = projectCommentRepository.findAllByParentId(projectComment.getParentId());
        if (childComments.isEmpty()){
            return;
        }
        projectCommentRepository.deleteAll(childComments);
    }

    private void assignParentId(ProjectCommentRequestDto projectCommentRequestDto, ProjectComment newComment) {
        // 만약 요청으로 들어온 부모 아이디가 부모 아이디를 가진다면, 이 코멘트 또한 최상단 부모아이디를 참조한다.
        Long requestParentId = projectCommentRequestDto.parentId();

        ProjectComment parentComment = projectCommentRepository.findById(requestParentId).orElseThrow(
                () -> new IllegalArgumentException("부모 댓글 아이디가 부적절하다.")
        );

        newComment.assignParentIdFrom(parentComment);
    }

}
