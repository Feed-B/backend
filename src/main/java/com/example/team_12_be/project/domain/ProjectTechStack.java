package com.example.team_12_be.project.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class ProjectTechStack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String techStack;

    @ManyToOne(fetch = FetchType.LAZY) // LAZY 로 하지 않아도 무방할듯
    @JoinColumn(name = "post_id")
    private Project project;

    public ProjectTechStack(String techStack) {
        this.techStack = techStack;
    }

    public void assign(Project project){
        this.project = project;
    }
}
