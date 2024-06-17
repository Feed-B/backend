package com.example.team_12_be.project.image;

import com.example.team_12_be.project.image.service.ProjectImageService;
import com.example.team_12_be.project.service.dto.request.ProjectImageDto;
import com.example.team_12_be.project.service.dto.request.ProjectRequestDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.IntStream;
@SecurityRequirement(name = "Bearer Authentication")
@RestController
@RequiredArgsConstructor
public class ImageTestController {

    private final ProjectImageService projectImageService;
    @PostMapping(value = "/s3/upload" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String s3upload(@Valid @RequestPart ProjectRequestDto projectRequestDto,
                           @RequestPart List<MultipartFile> multipartFiles,
                           @RequestPart List<Integer> indexes) {
          //file 형태를 데이터와 포함해서 요청하기엔 객체 바인딩 이슈로 개별로 받아서 record 생성
        List<ProjectImageDto> projectImageRequestDtos = IntStream.range(0, multipartFiles.size())
                .mapToObj(i -> new ProjectImageDto(multipartFiles.get(i),indexes.get(i)))
                .toList();

        projectImageService.upload(projectImageRequestDtos);
        return "업로드 테스트";
    }
}
