package com.example.team_12_be.post.presentation;

import com.example.team_12_be.member.domain.Member;
import com.example.team_12_be.post.application.ProjectCommentService;
import com.example.team_12_be.post.application.dto.ProjectCommentRequestDto;
import com.example.team_12_be.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProjectCommentController {

    private final ProjectCommentService projectCommentService;

    @PostMapping("/projects/{projectId}/comments")
    public ResponseEntity<Long> addComment(@PathVariable Long projectId,
                                           @AuthenticationPrincipal CustomUserDetails userDetails,
                                           @RequestBody ProjectCommentRequestDto projectCommentRequestDto) {

        Member member = userDetails.getMember();
        Long commentId = projectCommentService.addComment(projectId, member.getId(), projectCommentRequestDto);

        return ResponseEntity.ok(commentId);
    }

}
