package com.example.team_12_be.project.domain.comment.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class StarRank {

    @Column(name = "idea_rank")
    private float ideaRank;

    @Column(name = "design_rank")
    private float designRank;

    @Column(name = "function_rank")
    private float functionRank;

    @Column(name = "completion_rank")
    private float completionRank;

    private void validateRank(float rank) {
        if (rank < 0.0f || rank > 5.0f) {
            throw new IllegalArgumentException("1~5 사이의 값이어야 한다.");
        }
        float remainder = rank % 1;
        if (remainder != 0.0f && remainder != 0.5f) {
            throw new IllegalArgumentException("0.5점 단위로 존재해야 한다.");
        }
    }

    public StarRank(float ideaRank, float designRank, float functionRank, float completionRank) {
        validateRank(ideaRank);
        validateRank(designRank);
        validateRank(functionRank);
        validateRank(completionRank);

        this.ideaRank = ideaRank;
        this.designRank = designRank;
        this.functionRank = functionRank;
        this.completionRank = completionRank;
    }

    // 소수 첫째자리까지 반올림한다.
    public float getAverageRank(){
        float rawAverage = (ideaRank + designRank + functionRank + completionRank) / 4f;
        float roundPolicy = 10f;
        return Math.round(rawAverage * roundPolicy) / roundPolicy;
    }
}
