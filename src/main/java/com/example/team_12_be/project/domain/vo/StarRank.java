package com.example.team_12_be.project.domain.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class StarRank {

    @Column(name = "average_rank")
    private float averageRank;

    @Column(name = "idea_rank")
    private float ideaRank;

    @Column(name = "design_rank")
    private float designRank;

    @Column(name = "function_rank")
    private float functionRank;

    @Column(name = "completion_rank")
    private float completionRank;

    private static void validateRank(float rank) {
        if (rank < 1.0f || rank > 5.0f) {
            throw new IllegalArgumentException("1~5 사이의 값이어야 한다.");
        }
        float remainder = rank % 1;
        if (remainder != 0.0f && remainder != 0.5f) {
            throw new IllegalArgumentException("0.5점 단위로 존재해야 한다.");
        }
    }

    public static StarRank of(float ideaRank, float designRank, float functionRank, float completionRank) {
        validateRank(ideaRank);
        validateRank(designRank);
        validateRank(functionRank);
        validateRank(completionRank);

        return new StarRank(ideaRank, designRank, functionRank, completionRank);
    }

    // TODO : 총점 평균도 반올림 할것인지?
    public static StarRank ofAverage(float ideaRank, float designRank, float functionRank, float completionRank) {
        float avgIdeaRank = round(ideaRank);
        float avgDesignRank = round(designRank);
        float avgFunctionRank = round(functionRank);
        float avgCompletionRank = round(completionRank);

        return new StarRank(avgIdeaRank, avgDesignRank, avgFunctionRank, avgCompletionRank);
    }

    private StarRank(float ideaRank, float designRank, float functionRank, float completionRank){
        this.ideaRank = ideaRank;
        this.designRank = designRank;
        this.functionRank = functionRank;
        this.completionRank = completionRank;
        this.averageRank = calculateAverage();
    }


    private float calculateAverage(){
        float rawAverage = (ideaRank + designRank + functionRank + completionRank) / 4f;
        return round(rawAverage);
    }

    // 소수 첫째자리까지 반올림한다.
    private static float round(float value){
        float roundPolicy = 10f;
        return Math.round(value * roundPolicy) / roundPolicy;
    }
}
