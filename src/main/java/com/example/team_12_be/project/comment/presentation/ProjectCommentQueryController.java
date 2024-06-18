package com.example.team_12_be.project.comment.presentation;

import com.example.team_12_be.global.page.CustomPageResponse;
import com.example.team_12_be.global.presentation.CustomPageRequest;
import com.example.team_12_be.project.comment.service.ProjectCommentQueryService;
import com.example.team_12_be.project.comment.service.dto.MyProjectCommentResponse;
import com.example.team_12_be.project.comment.service.dto.ProjectCommentResponseDto;
import com.example.team_12_be.project.comment.service.dto.ReplyCommentResponseDto;
import com.example.team_12_be.security.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "프로젝트 댓글 GET(조회) 컨트롤러")
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class ProjectCommentQueryController {

    private final ProjectCommentQueryService projectCommentQueryService;

    @GetMapping("/{projectId}/comments")
    @Operation(description = "프로젝트에 달린 댓글 조회")
    public CustomPageResponse<ProjectCommentResponseDto> getProjectComments(@PathVariable Long projectId,
                                                                           @ModelAttribute CustomPageRequest customPageRequest) {
        Pageable pageable = PageRequest.of(customPageRequest.page(), customPageRequest.size());

        return projectCommentQueryService.findProjectCommentsByProjectId(projectId, pageable);
    }

    @GetMapping("/projects/{projectId}/comments/{commentId}")
    @Operation(description = "프로젝트에 달린 댓글 상세 조회")
    public ProjectCommentResponseDto getProjectCommentDetail(@PathVariable Long projectId, @PathVariable Long commentId){
        return projectCommentQueryService.getProjectCommentResponseDto(projectId, commentId);
    }

    @GetMapping("/projects/{projectId}/comments/{commentId}/replies")
    @Operation(description = "프로젝트에 달린 댓글에 달린 대댓글 조회")
    public CustomPageResponse<ReplyCommentResponseDto> findAllReplyByParentCommentId(@PathVariable Long projectId,
                                                                                     @PathVariable Long commentId,
                                                                                     @ModelAttribute CustomPageRequest customPageRequest){

        Pageable pageable = PageRequest.of(customPageRequest.page(), customPageRequest.size());

        return projectCommentQueryService.findAllReplyByParentCommentId(commentId, pageable);
    }

    @GetMapping("/projects/{projectId}/comments/mine")
    @Operation(description = "프로젝트에 달린 나의 댓글 조회 - (exists 로 존재여부 판별)")
    public MyProjectCommentResponse getMyComment(@PathVariable Long projectId,
                                                 @AuthenticationPrincipal CustomUserDetails customUserDetails){
        ProjectCommentResponseDto myProjectComment = projectCommentQueryService.getMyProjectComment(projectId, customUserDetails.getMember().getId());

        if (myProjectComment == null) {
            return new MyProjectCommentResponse(false, null);
        }

        return new MyProjectCommentResponse(true, myProjectComment);
    }
}