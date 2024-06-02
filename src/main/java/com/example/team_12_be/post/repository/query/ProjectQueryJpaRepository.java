package com.example.team_12_be.post.repository.query;

import com.example.team_12_be.post.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectQueryJpaRepository extends JpaRepository<Project, Long> {
}
