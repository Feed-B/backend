package com.example.team_12_be.post.domain;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository {

    Project save(Project project);

    void deleteById(Long projectId);

    Optional<Project> findById(Long projectId);
}
