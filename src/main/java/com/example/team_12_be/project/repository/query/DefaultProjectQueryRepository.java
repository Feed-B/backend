package com.example.team_12_be.project.repository.query;

import com.example.team_12_be.project.domain.Project;
import com.example.team_12_be.project.domain.ProjectQueryRepository;
import com.example.team_12_be.project.domain.ProjectRating;
import com.example.team_12_be.project.repository.ProjectRatingJpaRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DefaultProjectQueryRepository implements ProjectQueryRepository {

    private final ProjectQueryJpaRepository projectQueryJpaRepository;

    private final ProjectRatingJpaRepository projectRatingJpaRepository;

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<Project> findById(Long projectId) {
        return projectQueryJpaRepository.findById(projectId);
    }

    @Override
    public Optional<ProjectRating> findProjectRatingByMemberIdAndProjectId(Long memberId, Long projectId) {
        return projectRatingJpaRepository.findByMemberIdAndProjectId(memberId, projectId);
    }
}
