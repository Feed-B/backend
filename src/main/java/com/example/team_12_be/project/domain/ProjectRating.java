package com.example.team_12_be.project.domain;

import com.example.team_12_be.base.TimeStamp;
import com.example.team_12_be.member.domain.Member;
import com.example.team_12_be.project.domain.vo.StarRank;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table(indexes = {
        @Index(name = "idx_project_member", columnList = "member_id, project_id", unique = true)
})
public class ProjectRating extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Embedded
    private StarRank starRank;

    public ProjectRating(Member member, Project project, StarRank starRank) {
        this.member = member;
        this.project = project;
        this.starRank = starRank;
    }

    public void updateRank(StarRank starRank) {
        this.starRank = starRank;
    }

    public void assignToProject(Project project) {
        this.project = project;
    }
}
