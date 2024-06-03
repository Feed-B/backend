package com.example.team_12_be.project.service;

import com.example.team_12_be.project.service.dto.response.ProjectDetailResponseDto;
import com.example.team_12_be.project.domain.Project;
import com.example.team_12_be.project.domain.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProjectQueryService {

    private final ProjectRepository projectRepository;

    public Project findById(Long id) {
        return projectRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Project with id " + id + " not found")
        );
    }

    public ProjectDetailResponseDto getDetailById(Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new NoSuchElementException("Project with projectId " + projectId + " not found"));
        return ProjectDetailResponseDto.of(project);
    }
}
