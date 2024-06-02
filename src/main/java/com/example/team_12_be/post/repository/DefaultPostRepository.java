package com.example.team_12_be.post.repository;

import com.example.team_12_be.post.domain.Project;
import com.example.team_12_be.post.domain.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DefaultPostRepository implements ProjectRepository {

    private final ProejctJpaRepository proejctJpaRepository;

    @Override
    public Project save(Project project) {
        return proejctJpaRepository.save(project);
    }

    @Override
    public void deleteById(Long projectId) {
        proejctJpaRepository.deleteById(projectId);
    }

    @Override
    public Optional<Project> findById(Long projectId) {
        return proejctJpaRepository.findById(projectId);
    }
}
