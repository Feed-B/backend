package com.example.team_12_be.project.comment.domain;

import com.example.team_12_be.base.TimeStamp;
import com.example.team_12_be.member.domain.Member;
import com.example.team_12_be.project.domain.Project;
import com.example.team_12_be.project.domain.vo.StarRank;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
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
public class ProjectComment extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long parentId;

    @Column(nullable = false)
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Project project;

    @Embedded
    private StarRank starRank;

    public ProjectComment(Long parentId, String comment, Project project, Member member) {
        this.parentId = parentId;
        this.comment = comment;
        this.project = project;
        this.member = member;
    }

    public void assignParentIdFrom(ProjectComment parentComment) {
        if (parentComment.getParentId() == null){
            return;
        }
        this.parentId = parentComment.getParentId();
    }

    public void updateContent(String content) {
        this.comment = content;
    }
}
