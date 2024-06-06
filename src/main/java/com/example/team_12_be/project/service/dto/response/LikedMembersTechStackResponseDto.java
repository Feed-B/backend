package com.example.team_12_be.project.service.dto.response;

import com.example.team_12_be.member.domain.vo.TechStackValue;

public record LikedMembersTechStackResponseDto(TechStackValue techStackValue, Long count) {

}
