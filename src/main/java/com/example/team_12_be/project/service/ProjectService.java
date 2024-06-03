package com.example.team_12_be.project.service;

import com.example.team_12_be.member.domain.Member;
import com.example.team_12_be.project.service.dto.request.ProjectRequestDto;
import com.example.team_12_be.project.domain.Project;
import com.example.team_12_be.project.domain.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public void saveProject(ProjectRequestDto projectRequestDto, Member author){
        Project project = projectRequestDto.toEntity(author);
        projectRepository.save(project);
    }

    public void deleteProject(Long projectId){
        projectRepository.deleteById(projectId);
    }


}