package com.example.team_12_be.project.comment.presentation;

import com.example.team_12_be.global.page.CustomPageResponse;
import com.example.team_12_be.project.comment.service.dto.ProjectCommentResponseDto;
import com.example.team_12_be.project.comment.service.ProjectCommentQueryService;
import com.example.team_12_be.project.comment.service.dto.ReplyCommentResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "프로젝트 댓글 GET(조회) 컨트롤러")
@RestController
@RequiredArgsConstructor
public class ProjectCommentQueryController {

    private final ProjectCommentQueryService projectCommentQueryService;

    @GetMapping("/{projectId}/comments")
    public CustomPageResponse<ProjectCommentResponseDto> getProjectComments(@PathVariable Long projectId,
                                                                            @RequestParam(defaultValue = "1") int page,
                                                                            @RequestParam(defaultValue = "10") int size,
                                                                            @RequestParam(defaultValue = "100") int limit) {
        if (page < 1) {
            page = 1;
        }

        int adjustedPageSize = Math.min(size, limit);
        Pageable pageable = PageRequest.of(page, adjustedPageSize);

        return projectCommentQueryService.findProjectCommentsByProjectId(projectId, pageable);
    }

    @GetMapping("/projects/{projectId}/comments/{commentId}")
    public ProjectCommentResponseDto getProjectCommentDetail(@PathVariable Long projectId, @PathVariable Long commentId){
        return projectCommentQueryService.getProjectCommentResponseDto(projectId, commentId);
    }

    @GetMapping("/projects/{projectId}/comments/{commentId}/replies")
    public CustomPageResponse<ReplyCommentResponseDto> findAllReplyByParentCommentId(@PathVariable Long projectId,
                                                                                     @PathVariable Long commentId,
                                                                                     @RequestParam(defaultValue = "1") int page,
                                                                                     @RequestParam(defaultValue = "10") int size,
                                                                                     @RequestParam(defaultValue = "100") int limit){

        if (page < 1) {
            page = 1;
        }

        int adjustedPageSize = Math.min(size, limit);
        Pageable pageable = PageRequest.of(page, adjustedPageSize);

        return projectCommentQueryService.findAllReplyByParentCommentId(commentId, pageable);
    }
}