package com.example.team_12_be.project.domain;

import com.example.team_12_be.base.TimeStamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Entity
@NoArgsConstructor
@Getter
public class ProjectImage extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id" , nullable = false)
    private Project project;

    private String url;

    @Column(name = "index")
    private int index;

    public ProjectImage(String url , int index) {
        this.url = url;
        this.index = index;
    }

    public void assign(Project project) {
        this.project = project;
    }
}
