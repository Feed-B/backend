package com.example.team_12_be.post.application;

import com.example.team_12_be.post.domain.Project;
import com.example.team_12_be.post.domain.ProjectRepository;
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
}
