package com.example.team_12_be.project.domain;

import com.example.team_12_be.base.TimeStamp;
import com.example.team_12_be.member.domain.Member;
import com.example.team_12_be.project.comment.domain.ProjectComment;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Project extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String introductions;

    private Long viewCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member author;

    private String thumbnailUrl;

    private String serviceUrl;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProjectTechStack> projectTechStacks = new ArrayList<>();

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PrjectImage> projectImages = new ArrayList<>();

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProjectComment> projectComments = new ArrayList<>();

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProjectLike> projectLikes = new ArrayList<>();

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProjectTeammate> projectTeammates = new ArrayList<>();

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProjectLink> projectLinks = new ArrayList<>();

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProjectRating> projectRatings = new ArrayList<>();

    public Project(String title, String introductions, Member author, String serviceUrl, List<ProjectTechStack> projectTechStacks, List<ProjectTeammate> projectTeammates, List<ProjectLink> projectLinks) {
        this.title = title;
        this.introductions = introductions;
        this.viewCount = 0L;
        this.author = author;
        this.serviceUrl = serviceUrl;
        this.projectTechStacks = projectTechStacks;
        this.projectTeammates = projectTeammates;
        this.projectLinks = projectLinks;

        // TODO : images
//        this.thumbnail = thumbnail;
//        this.projectImages = projectImages;
    }

    public void addProjectRatings(ProjectRating projectRating){
        projectRatings.add(projectRating);
        projectRating.assignToProject(this);
    }

    public void removeProjectRatings(ProjectRating projectRating){
        projectRatings.remove(projectRating);
        projectRating.assignToProject(null);
    }
}