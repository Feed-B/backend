package com.example.team_12_be.project.repository;

import com.example.team_12_be.project.domain.ProjectImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectImageJpaRepository extends JpaRepository<ProjectImage , Long> {
    ProjectImage findByIndex(int idx);
}
