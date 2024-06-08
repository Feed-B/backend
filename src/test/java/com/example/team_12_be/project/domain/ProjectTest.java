package com.example.team_12_be.project.domain;

import com.example.team_12_be.member.domain.Member;
import com.example.team_12_be.project.domain.vo.StarRank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class ProjectTest {

    private Project project;
    private Member author;

    @BeforeEach
    public void setUp() {
        author = mock(Member.class);

        project = new Project(
                "Test Project",
                "Test introduction",
                author,
                "http://service.url"
        );
    }

    @Test
    void testCalculateAverageStarRank_NoRatings() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            project.calculateAverageStarRank();
        });
        assertEquals("No ratings available.", exception.getMessage());
    }

    @Test
    void testCalculateAverageStarRank_WithRatings() {
        ProjectRating rating1 = new ProjectRating(author, project, StarRank.of(3, 4, 5, 2));
        ProjectRating rating2 = new ProjectRating(author, project, StarRank.of(5, 3, 4, 3));
        ProjectRating rating3 = new ProjectRating(author, project, StarRank.of(4, 4, 3, 5));

        project.addProjectRating(rating1);
        project.addProjectRating(rating2);
        project.addProjectRating(rating3);

        StarRank averageRank = project.calculateAverageStarRank();

        assertEquals(3.8f, averageRank.getAverageRank());
        assertEquals(4.0f, averageRank.getIdeaRank());
        assertEquals(3.7f, averageRank.getDesignRank(), 0.01f);
        assertEquals(4.0f, averageRank.getFunctionRank());
        assertEquals(3.3f, averageRank.getCompletionRank(), 0.01f);
    }

    @Test
    void testAddAndRemoveProjectRatings() {
        ProjectRating rating1 = new ProjectRating(author, project, StarRank.of(3, 4, 5, 2));

        project.addProjectRating(rating1);
        assertEquals(1, project.getProjectRatings().size());

        project.removeProjectRating(rating1);
        assertEquals(0, project.getProjectRatings().size());
    }
}