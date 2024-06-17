package com.example.team_12_be.project.presentation;

import com.example.team_12_be.project.service.ProjectService;
import com.example.team_12_be.project.service.dto.request.ProjectImageDto;
import com.example.team_12_be.project.service.dto.request.ProjectRatingRequestDto;
import com.example.team_12_be.project.service.dto.request.ProjectRequestDto;
import com.example.team_12_be.security.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.IntStream;

@Tag(name = "프로젝트 기능(C,U,D) 컨트롤러")
@SecurityRequirement(name = "Bearer Authentication")
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class ProjectController {

    private final ProjectService projectService;

    // TODO : Authentication 완료되면 작성 유저의 정보도 받아야 한다.

    @PostMapping(value ="/projects" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(description="프로젝트를 생성")
    public void saveProject(@RequestPart ProjectRequestDto projectRequestDto,
                            @RequestPart List<MultipartFile> multipartFileList,
                            @RequestPart List<Integer> indexes,
                            @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        //file 형태를 데이터와 포함해서 요청하기엔 객체 바인딩 이슈로 개별로 받아서 record 생성
        List<ProjectImageDto> projectImageDtoList = IntStream.range(0, multipartFileList.size())
                .mapToObj(i -> new ProjectImageDto(multipartFileList.get(i),indexes.get(i)))
                .toList();


        projectService.saveProject(projectRequestDto, customUserDetails.getMember() , projectImageDtoList);
    }

    @DeleteMapping("/projects/{projectId}")
    @Operation(description="프로젝트를 삭제")
    public void deleteProject(@PathVariable Long projectId) {
        projectService.deleteProject(projectId);
    }

    @PostMapping("/projects/{projectId}/rank")
    @Operation(description="프로젝트에 대한 사용자의 별점 생성")
    public void addRating(@PathVariable Long projectId,
                          @AuthenticationPrincipal CustomUserDetails customUserDetails,
                          @RequestBody ProjectRatingRequestDto projectRatingRequestDto) {

        projectService.addRating(projectId, customUserDetails.getMember().getId(), projectRatingRequestDto);
    }

    @PostMapping("/projects/{projectId}/like")
    @Operation(description="프로젝트에 대한 사용자의 좋아요 생성")
    public void likeProject(@PathVariable Long projectId,
                            @AuthenticationPrincipal CustomUserDetails customUserDetails){

        Long memberId = customUserDetails.getMember().getId();
        projectService.likeProject(memberId, projectId);
    }

    @DeleteMapping("/projects/{projectId}/unlike")
    @Operation(description="프로젝트에 대한 사용자의 좋아요 삭제")
    public void unlikeProject(@PathVariable Long projectId,
                            @AuthenticationPrincipal CustomUserDetails customUserDetails){

        Long memberId = customUserDetails.getMember().getId();
        projectService.unlikeProject(memberId, projectId);
    }

    // TODO : 프로젝트 수정 API 추가
}