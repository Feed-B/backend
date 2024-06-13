package com.example.team_12_be.project.comment.presentation;

import com.example.team_12_be.member.domain.Member;
import com.example.team_12_be.project.comment.service.ProjectCommentService;
import com.example.team_12_be.project.comment.service.dto.ProjectCommentRequestDto;
import com.example.team_12_be.security.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "프로젝트 댓글 C,U,D 컨트롤러")
@RestController
@RequiredArgsConstructor
public class ProjectCommentController {

    private final ProjectCommentService projectCommentService;

    @PostMapping("/projects/{projectId}/comments")
    @Operation(description="프로젝트 댓글 추가")
    public ResponseEntity<Long> addComment(@PathVariable Long projectId,
                                           @AuthenticationPrincipal CustomUserDetails userDetails,
                                           @RequestBody ProjectCommentRequestDto projectCommentRequestDto) {

        Member member = userDetails.getMember();
        Long commentId = projectCommentService.addComment(projectId, member.getId(), projectCommentRequestDto);

        return ResponseEntity.ok(commentId);
    }

    // TODO : 대댓글 API 추가
}
