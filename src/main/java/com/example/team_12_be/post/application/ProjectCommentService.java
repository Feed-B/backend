package com.example.team_12_be.post.application;

import com.example.team_12_be.member.application.MemberService;
import com.example.team_12_be.member.domain.Member;
import com.example.team_12_be.post.application.dto.ProjectCommentRequestDto;
import com.example.team_12_be.post.domain.Project;
import com.example.team_12_be.post.domain.comment.ProjectComment;
import com.example.team_12_be.post.domain.comment.ProjectCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectCommentService {

    private final ProjectCommentRepository projectCommentRepository;

    private final ProjectQueryService projectQueryService;

    private final MemberService memberService;

    public Long addComment(Long projectId, Long userId, ProjectCommentRequestDto projectCommentRequestDto) {
        Project commnetedProject = projectQueryService.findById(projectId);
        Member commentAuthor = memberService.findById(userId);

        ProjectComment newComment = projectCommentRequestDto.toEntity(commnetedProject, commentAuthor);
        projectCommentRepository.save(newComment);

        this.assignParentId(projectCommentRequestDto, newComment);

        return newComment.getId();
    }

    // 만약 요청으로 들어온 부모 아이디가 부모 아이디를 가진다면, 이 코멘트 또한 최상단 부모아이디를 참조한다.
    private void assignParentId(ProjectCommentRequestDto projectCommentRequestDto, ProjectComment newComment) {
        Long requestParentId = projectCommentRequestDto.parentId();

        if (requestParentId == null){
            return;
        }

        ProjectComment parentComment = projectCommentRepository.findById(requestParentId).orElseThrow();
        newComment.assignParentIdFrom(parentComment);
    }
}
