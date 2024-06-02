package com.example.team_12_be.post.repository.query;

import com.example.team_12_be.post.domain.ProjectQueryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DefaultProjectQueryRepository implements ProjectQueryRepository {

    private final ProjectQueryJpaRepository projectQueryJpaRepository;

    private final JPAQueryFactory jpaQueryFactory;


}
