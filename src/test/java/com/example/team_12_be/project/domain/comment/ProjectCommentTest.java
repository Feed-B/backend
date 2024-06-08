package com.example.team_12_be.project.domain.comment;

import com.example.team_12_be.member.domain.Member;
import com.example.team_12_be.project.comment.domain.ProjectComment;
import com.example.team_12_be.project.comment.repository.comment.ProjectCommentJpaRepository;
import com.example.team_12_be.project.domain.Project;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@DataJpaTest
class ProjectCommentTest {

    @Autowired
    ProjectCommentJpaRepository projectCommentRepository;

    @Test
    @DisplayName("부모 댓글 정보를 등록하는 데 성공한다.")
    void testAssignParentIdFrom() {
        Member member = mock(Member.class);
        Project project = mock(Project.class);

        // 부모 코멘트
        ProjectComment parentComment = new ProjectComment(null, "comment1",  project, member);
        projectCommentRepository.save(parentComment);

        // 부모 코멘트에 대한 대댓글
        ProjectComment childComment = new ProjectComment(parentComment.getId(), "comment2",  project, member);
        projectCommentRepository.save(childComment);
        childComment.assignParentIdFrom(parentComment);

        assertThat(childComment.getParentId()).isEqualTo(parentComment.getId());
    }

    @Test
    @DisplayName("부모 댓글이 부모 댓글(즉 최상단 댓글) 을 가질 경우, 생성되는 자식 댓글은 최상단 댓글을 참조한다.")
    void testTopParentCommentIsAssigned() {
        Member member = mock(Member.class);
        Project project = mock(Project.class);

        // 최상위 코멘트
        ProjectComment topComment = new ProjectComment(null, "comment3", project, member);
        projectCommentRepository.save(topComment);

        // 부모 코멘트
        ProjectComment parentComment = new ProjectComment(topComment.getId(), "comment1", project, member);
        projectCommentRepository.save(parentComment);
        parentComment.assignParentIdFrom(topComment);

        // 부모 코멘트에 대한 대댓글
        ProjectComment childComment = new ProjectComment(parentComment.getId(), "comment2",  project, member);
        projectCommentRepository.save(childComment);
        childComment.assignParentIdFrom(parentComment);

        // 최하위 대댓글도 최상위 부모 댓글을 참조한다
        assertThat(childComment.getParentId()).isEqualTo(topComment.getId());
    }
}