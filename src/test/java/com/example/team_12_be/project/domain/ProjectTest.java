package com.example.team_12_be.project.domain;

import com.example.team_12_be.member.domain.Member;
import com.example.team_12_be.project.domain.vo.StarRank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class ProjectTest {

    private Project project;
    private Member author;

    @BeforeEach
    public void setUp() {
        author = mock(Member.class);

        ProjectTechStack techStack1 = mock(ProjectTechStack.class);
        ProjectTechStack techStack2 = mock(ProjectTechStack.class);
        ProjectTeammate teammate1 = mock(ProjectTeammate.class);
        ProjectTeammate teammate2 = mock(ProjectTeammate.class);
        ProjectLink link1 = mock(ProjectLink.class);
        ProjectLink link2 = mock(ProjectLink.class);

        project = new Project(
                "Test Project",
                "Test introduction",
                author,
                "http://service.url",
                Arrays.asList(techStack1, techStack2),
                Arrays.asList(teammate1, teammate2),
                Arrays.asList(link1, link2)
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

        project.addProjectRatings(rating1);
        project.addProjectRatings(rating2);
        project.addProjectRatings(rating3);

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

        project.addProjectRatings(rating1);
        assertEquals(1, project.getProjectRatings().size());

        project.removeProjectRatings(rating1);
        assertEquals(0, project.getProjectRatings().size());
    }
}