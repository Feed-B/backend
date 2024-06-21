package com.example.team_12_be.project.domain;

import com.example.team_12_be.base.TimeStamp;
import com.example.team_12_be.member.domain.Member;
import com.example.team_12_be.project.comment.domain.ProjectComment;
import com.example.team_12_be.project.domain.vo.ImageType;
import com.example.team_12_be.project.domain.vo.StarRank;
import jakarta.persistence.*;
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

    private String content;

    private Long viewCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member author;

    private String thumbnailUrl;

    private String serviceUrl;

    @Enumerated(EnumType.STRING)
    private ImageType imageType;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProjectTechStack> projectTechStacks = new ArrayList<>();

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OrderBy("index ASC")  // index 값에 따라 오름차순 정렬
    private List<ProjectImage> projectImages = new ArrayList<>();

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

    public Project(String title, String introductions, String content, Member author, String serviceUrl , ImageType imageType) {
        this.title = title;
        this.introductions = introductions;
        this.content = content;
        this.viewCount = 0L;
        this.author = author;
        this.serviceUrl = serviceUrl;
        this.imageType = imageType;
        // TODO : images
//        this.thumbnail = thumbnail;
//        this.projectImages = projectImages;
    }

    public void updateProject(String title, String introductions, String content,  String serviceUrl) {
        this.title = title;
        this.introductions = introductions;
        this.content = content;
        this.viewCount = 0L;
        this.serviceUrl = serviceUrl;

    }

    public void addTechStack(ProjectTechStack projectTechStack){
        projectTechStacks.add(projectTechStack);
        projectTechStack.assign(this);
    }

    public void removeTechStack(ProjectTechStack projectTechStack){
        projectTechStacks.remove(projectTechStack);
        projectTechStack.assign(null);
    }

    public void addTeammate(ProjectTeammate projectTeammate){
        projectTeammates.add(projectTeammate);
        projectTeammate.assign(this);
    }

    public void removeTeammate(ProjectTeammate projectTeammate){
        projectTeammates.remove(projectTeammate);
        projectTeammate.assign(null);
    }

    public void addLink(ProjectLink projectLink){
        projectLinks.add(projectLink);
        projectLink.assign(this);
    }

    public void removeLink(ProjectLink projectLink){
        projectLinks.remove(projectLink);
        projectLink.assign(null);
    }


    public void addProjectLike(ProjectLike projectLike){
        projectLikes.add(projectLike);
        projectLike.assign(this);
    }

    public void removeProjectLike(ProjectLike projectLike){
        projectLikes.remove(projectLike);
        projectLike.assign(null);
    }


    public void addProjectRating(ProjectRating projectRating){
        projectRatings.add(projectRating);
        projectRating.assignToProject(this);
    }

    public void removeProjectRating(ProjectRating projectRating){
        projectRatings.remove(projectRating);
        projectRating.assignToProject(null);
    }

    public void addProjectImage(ProjectImage projectImage) {
        projectImages.add(projectImage);
        projectImage.assign(this);
    }
    public void removeProjectImage(ProjectImage projectImage) {
        projectImages.remove(projectImage);
        projectImage.assign(null);
    }

    public void addThumbnailUrl(String url) {
        this.thumbnailUrl = url;
    }
    public StarRank calculateAverageStarRank() {
        int size = projectRatings.size();
        if (size == 0) {
            throw new IllegalArgumentException("No ratings available.");
        }

        float totalIdeaRank = 0f, totalDesignRank = 0f, totalFunctionRank = 0f, totalCompletionRank = 0f;

        for (ProjectRating projectRating : projectRatings) {
            StarRank starRank = projectRating.getStarRank();
            totalIdeaRank += starRank.getIdeaRank();
            totalDesignRank += starRank.getDesignRank();
            totalFunctionRank += starRank.getFunctionRank();
            totalCompletionRank += starRank.getCompletionRank();
        }

        float avgIdeaRank = totalIdeaRank / size;
        float avgDesignRank = totalDesignRank / size;
        float avgFunctionRank = totalFunctionRank / size;
        float avgCompletionRank = totalCompletionRank / size;

        return StarRank.ofAverage(avgIdeaRank, avgDesignRank, avgFunctionRank, avgCompletionRank);
    }
}