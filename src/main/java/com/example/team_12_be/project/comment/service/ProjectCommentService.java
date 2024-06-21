package com.example.team_12_be.project.comment.service;

import com.example.team_12_be.member.service.dto.MemberService;
import com.example.team_12_be.member.domain.Member;
import com.example.team_12_be.project.service.ProjectQueryService;
import com.example.team_12_be.project.comment.service.dto.ProjectCommentRequestDto;
import com.example.team_12_be.project.domain.Project;
import com.example.team_12_be.project.comment.domain.ProjectComment;
import com.example.team_12_be.project.comment.domain.ProjectCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProjectCommentService {

    private final ProjectCommentRepository projectCommentRepository;

    private final ProjectQueryService projectQueryService;

    private final MemberService memberService;

    @Transactional
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
