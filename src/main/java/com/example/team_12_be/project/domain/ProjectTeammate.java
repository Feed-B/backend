package com.example.team_12_be.project.domain;

import com.example.team_12_be.member.domain.vo.Job;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ProjectTeammate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "projectId", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Project project;

    private String teammateName;

    @Enumerated(EnumType.STRING)
    private Job job;

    private String url;

    public ProjectTeammate(String teammateName, Job job) {
        this.teammateName = teammateName;
        this.job = job;
    }

    public void assign(Project project){
        this.project = project;
    }
}
