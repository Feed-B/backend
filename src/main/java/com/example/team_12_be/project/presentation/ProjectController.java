package com.example.team_12_be.project.presentation;

import com.example.team_12_be.project.service.DefaultProjectUpdateService;
import com.example.team_12_be.project.service.ProjectService;
import com.example.team_12_be.project.service.dto.request.ProjectImageDto;
import com.example.team_12_be.project.service.dto.request.ProjectRatingRequestDto;
import com.example.team_12_be.project.service.dto.request.ProjectRequestDto;
import com.example.team_12_be.project.service.dto.request.ProjectThumbnailDto;
import com.example.team_12_be.project.service.usecase.update.dto.request.ProjectUpdateRequestDto;
import com.example.team_12_be.security.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.IntStream;

@Tag(name = "프로젝트 기능(C,U,D) 컨트롤러")
@SecurityRequirement(name = "Bearer Authentication")
@RestController
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    private final DefaultProjectUpdateService projectUpdateService;

    // TODO : Authentication 완료되면 작성 유저의 정보도 받아야 한다.

    @PostMapping(value ="/projects" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(description="프로젝트를 생성")
    public ResponseEntity<Void> saveProject(@RequestPart ProjectRequestDto projectRequestDto,
                                      @RequestPart List<MultipartFile> images,
                                      @RequestPart List<Integer> imageIndexes,
                                      @RequestPart MultipartFile thumbnail,
                                      @RequestPart Integer thumbnailIndex,
                                      @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        //file 형태를 데이터와 포함해서 요청하기엔 객체 바인딩 이슈로 개별로 받아서 record 생성
        List<ProjectImageDto> projectImageDtoList = IntStream.range(0, images.size())
                .mapToObj(i -> new ProjectImageDto(images.get(i),imageIndexes.get(i)))
                .toList();

        ProjectThumbnailDto projectThumbnailDto = new ProjectThumbnailDto(thumbnail , thumbnailIndex);

        Long projectId = projectService.saveProject(projectRequestDto, customUserDetails.getMember(),projectImageDtoList , projectThumbnailDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(projectId)
                .toUri();

        return ResponseEntity.created(location).build();

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

    @PutMapping("/projects/{projectId}")
    public ResponseEntity<Void> updateProject(
            @PathVariable Long projectId,
            @RequestBody ProjectUpdateRequestDto projectRequestDto,
//                       @RequestPart List<MultipartFile> images,
//                       @RequestPart List<Integer> imageIndexes,
//                       @RequestPart MultipartFile thumbnail,
//                       @RequestPart Integer thumbnailIndex,
                       @AuthenticationPrincipal CustomUserDetails customUserDetails){
//        List<ProjectImageDto> projectImages = IntStream.range(0, images.size() - 1)
//                .mapToObj(idx -> new ProjectImageDto(images.get(idx), imageIndexes.get(idx)))
//                .toList();
//        ProjectThumbnailDto projectThumbnailDto = new ProjectThumbnailDto(thumbnail, thumbnailIndex);
        Long memberId = customUserDetails.getMember().getId();
        projectUpdateService.updateProject(projectId, memberId, projectRequestDto);

//        projectUpdateService.updateProject(memberId, projectRequestDto, projectThumbnailDto, projectImages);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(projectId)
                .toUri();
        return ResponseEntity.created(location).build();
    }
}