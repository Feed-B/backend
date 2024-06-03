package com.example.team_12_be.project.service.dto;

import com.example.team_12_be.project.comment.service.dto.ProjectCommentRequestDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ProjectCommentRequestDtoTest {

    private static Validator validator;

    @BeforeAll
    public static void setupValidatorInstance() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("별점에 대한 입력값은 0~5 사이어야 하고, 0.5 단위로 끊겨야 한다.")
    void starRankValidationTest() {
        // arrange
        ProjectCommentRequestDto dto = new ProjectCommentRequestDto(null, "comment", 1L, 5f,
                0f, 4.5f);

        // act
        Set<ConstraintViolation<ProjectCommentRequestDto>> violations = validator.validate(dto);

        // assert
        assertTrue(violations.isEmpty());
    }

}