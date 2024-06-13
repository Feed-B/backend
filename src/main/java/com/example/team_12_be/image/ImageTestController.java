package com.example.team_12_be.image;

import com.example.team_12_be.image.service.ProjectImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ImageTestController {

    private final ProjectImageService projectImageService;
    @PostMapping("/s3/upload")
    public String s3upload(@RequestPart(value = "images") List<MultipartFile> images) {
        projectImageService.upload(images);
        return "업로드 테스트";
    }



}
