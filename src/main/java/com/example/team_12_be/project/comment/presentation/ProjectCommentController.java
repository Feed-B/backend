package com.example.team_12_be.project.comment.presentation;

import com.example.team_12_be.member.domain.Member;
import com.example.team_12_be.project.comment.service.ProjectCommentService;
import com.example.team_12_be.project.comment.service.dto.CommentUpdateRequestDto;
import com.example.team_12_be.project.service.dto.request.ProjectRatingRequestDto;
import com.example.team_12_be.security.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Tag(name = "프로젝트 댓글 C,U,D 컨트롤러")
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class ProjectCommentController {

    private final ProjectCommentService projectCommentService;

    @PostMapping("/projects/{projectId}/comments")
    @Operation(description="프로젝트에 대한 사용자의 별점, 댓글 생성")
    public ResponseEntity<Void> addRating(@PathVariable Long projectId,
                                          @AuthenticationPrincipal CustomUserDetails customUserDetails,
                                          @RequestBody ProjectRatingRequestDto projectRatingRequestDto) {

        Long commentId = projectCommentService.addCommentAndRating(projectId, customUserDetails.getMember().getId(), projectRatingRequestDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(commentId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/projects/{commentId}")
    @Operation(description="프로젝트 댓글, 별점 수정")
    public ResponseEntity<Long> updateComment(@PathVariable Long commentId,
                                           @AuthenticationPrincipal CustomUserDetails userDetails,
                                           @RequestBody CommentUpdateRequestDto commentUpdateRequestDto) {

        Member member = userDetails.getMember();
        projectCommentService.updateComment(commentId, member.getId(), commentUpdateRequestDto);

        return ResponseEntity.ok(commentId);
    }

    @DeleteMapping("/projects/comments/{commentId}")
    @Operation(description="프로젝트 댓글, 별점 삭제")
    public void deleteComment(@PathVariable Long commentId,
                              @AuthenticationPrincipal CustomUserDetails userDetails) {

        Member member = userDetails.getMember();
        projectCommentService.deleteComment(commentId, member.getId());
    }
}
