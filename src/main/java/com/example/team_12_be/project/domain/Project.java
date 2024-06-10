package com.example.team_12_be.project.domain;

import com.example.team_12_be.base.TimeStamp;
import com.example.team_12_be.member.domain.Member;
import com.example.team_12_be.project.comment.domain.ProjectComment;
import com.example.team_12_be.project.domain.vo.StarRank;
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

    private String content;

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

    public Project(String title, String introductions, String content, Member author, String serviceUrl) {
        this.title = title;
        this.introductions = introductions;
        this.content = content;
        this.viewCount = 0L;
        this.author = author;
        this.serviceUrl = serviceUrl;

        // TODO : images
//        this.thumbnail = thumbnail;
//        this.projectImages = projectImages;
    }

    public void addProjectLike(ProjectLike projectLike){
        projectLikes.add(projectLike);
        projectLike.assign(this);
    }

    public void addProjectRating(ProjectRating projectRating){
        projectRatings.add(projectRating);
        projectRating.assignToProject(this);
    }

    public void removeProjectRating(ProjectRating projectRating){
        projectRatings.remove(projectRating);
        projectRating.assignToProject(null);
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