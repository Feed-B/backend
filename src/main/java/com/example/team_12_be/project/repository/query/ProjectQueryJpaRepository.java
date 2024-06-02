package com.example.team_12_be.project.repository.query;

import com.example.team_12_be.project.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectQueryJpaRepository extends JpaRepository<Project, Long> {
}
