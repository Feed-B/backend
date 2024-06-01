package com.example.team_12_be.post.application;

import com.example.team_12_be.post.domain.Project;
import com.example.team_12_be.post.domain.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectQueryService {

    private final ProjectRepository projectRepository;

}
