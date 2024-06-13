package com.example.team_12_be.image.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import com.example.team_12_be.image.exception.ImgErrorCode;
import com.example.team_12_be.image.exception.ProjectImageException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectImageService { //TODO 단일책임의 원칙을 최대한 적용해보자.

    private final AmazonS3 amazonS3;

    private String bucketName = "feedb-bucket";
    private String bucketFolder = "image/";

    @Transactional
    public String upload(List<MultipartFile> images) {
        if(images.size() == 0 || Objects.isNull(images.getFirst().getOriginalFilename())) {
            throw new ProjectImageException(ImgErrorCode.EMPTY_FILE_EXCEPTION);
        }
        return this.uploadImage(images);
    }
    private String uploadImage(List<MultipartFile> images) {
        this.validateImageFileExtention(images);
        try{
            return this.uploadImageToS3(images);
        }catch (Exception e) {
            throw new ProjectImageException(ImgErrorCode.IO_EXCEPTION_ON_IMAGE_UPLOAD,e);
        }
    }
    //S3에 이미지 저장
    private String uploadImageToS3(List<MultipartFile> images) throws IOException {
        List<String> urls = new ArrayList<String>();

        for (MultipartFile image : images) {
            String originalFilename = image.getOriginalFilename(); // 원본 파일
            String extention = originalFilename.substring(originalFilename.lastIndexOf(".")); //파일 확장자

            String s3FileName = bucketFolder + "/" + UUID.randomUUID().toString().substring(0, 10)+ "_" + originalFilename; // s3 저장 파일명 ex) 랜덤값_원본파일명

            InputStream is = image.getInputStream();
            byte[] bytes = IOUtils.toByteArray(is);

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType("image/" + extention);
            metadata.setContentLength(bytes.length);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

            try {
                PutObjectRequest putObjectRequest =
                        new PutObjectRequest(bucketName , s3FileName , byteArrayInputStream , metadata)
                                .withCannedAcl(CannedAccessControlList.PublicRead);
                amazonS3.putObject(putObjectRequest); // put image to S3
            } catch (Exception e) {
                throw new ProjectImageException(ImgErrorCode.PUT_OBJECT_EXCEPTION , e);
            }finally {
                byteArrayInputStream.close();
                is.close();
            }
            String url = amazonS3.getUrl(bucketName , s3FileName).toString();
            log.info("url = " + url);
            urls.add(url); //S3에서 URL 가져오기
        }

        //TODO 여기서 url DB 저장 메소드 구현?
        return "이거야?";
    }

    //파일 형태 검증 메소드
    private void validateImageFileExtention(List<MultipartFile> images) {

       for(MultipartFile image : images) {
            String filename = image.getOriginalFilename();
            int lastDotIndex = filename.lastIndexOf(".");
            if(lastDotIndex == -1) {
                throw new ProjectImageException(ImgErrorCode.NO_FILE_EXTENTION); //TODO 에러 응답 형태 테스트!
            }

            String extention = filename.substring(lastDotIndex + 1).toLowerCase();
            List<String> allowedExtentionList = Arrays.asList("jpg" , "jpeg" , "png" , "gif");

            if(!allowedExtentionList.contains(extention)) {
                throw new ProjectImageException(ImgErrorCode.INVALID_FILE_EXTENTION);
            }
       }
    }




}
