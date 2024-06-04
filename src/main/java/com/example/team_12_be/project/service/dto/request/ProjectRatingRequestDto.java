package com.example.team_12_be.project.service.dto.request;

import com.example.team_12_be.global.validate.HalfOrWholeNumber;
import com.example.team_12_be.project.domain.vo.StarRank;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ProjectRatingRequestDto (
        @NotNull @Min(0) @Max(5) @HalfOrWholeNumber
        float ideaRank,

        @NotNull @Min(0) @Max(5) @HalfOrWholeNumber
        float designRank,

        @NotNull @Min(0) @Max(5) @HalfOrWholeNumber
        float functionRank,

        @NotNull @Min(0) @Max(5) @HalfOrWholeNumber
        float completionRank
) {
        public StarRank toStarRank(){
                return new StarRank(ideaRank, designRank, functionRank, completionRank);
        }
}
