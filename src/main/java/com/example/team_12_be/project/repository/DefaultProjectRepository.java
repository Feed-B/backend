package com.example.team_12_be.project.repository;

import com.example.team_12_be.project.domain.Project;
import com.example.team_12_be.project.domain.ProjectRating;
import com.example.team_12_be.project.domain.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// TODO : 루트 애그리거트인 Project 의 처리 범위가 늘어감에 따라 Repository 라는 이름보다 Port 라는 이름을 사용하는 것이 더 명확해 질 수 있다.
@Repository
@RequiredArgsConstructor
public class DefaultProjectRepository implements ProjectRepository {

    private final ProejctJpaRepository proejctJpaRepository;

    private final ProjectRatingJpaRepository projectRatingJpaRepository;

    /**
     * 자주 사용될 것이므로 QueryRepository 에 넘기지 않는다.
     * @param projectId Id
     * @return Optional<Project>
     */
    @Override
    public Optional<Project> findById(Long projectId) {
        return proejctJpaRepository.findById(projectId);
    }

    @Override
    public Project saveProject(Project project) {
        return proejctJpaRepository.save(project);
    }

    @Override
    public void deleteById(Long projectId) {
        proejctJpaRepository.deleteById(projectId);
    }

    @Override
    public boolean existsRatingByMemberAndProject(Long memberId, Long projectId) {
        return projectRatingJpaRepository.existsByMemberIdAndProjectId(memberId, projectId);
    }

    @Override
    public ProjectRating saveProjectRating(ProjectRating projectRating) {
        return projectRatingJpaRepository.save(projectRating);
    }
}
