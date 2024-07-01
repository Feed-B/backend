package com.example.team_12_be.project.rating.comment.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;

public interface ProjectCommentRepository {

    RatingReply save(RatingReply ratingReply);

    Optional<RatingReply> findById(Long id);

    // 혹은 pageAble
    Slice<RatingReply> findAllByProjectIdWithMember(Long projectId, Pageable pageable);

    long countByParentCommentId(Long parentCommentId);

    Page<RatingReply> findAllByParentCommentId(Long parentCommentId, Pageable pageable);

    Optional<RatingReply> findByProjectIdAndMemberId(Long projectId, Long memberId);

    Optional<RatingReply> findByIdAndMemberId(Long id, Long memberId);

    List<RatingReply> findAllByParentId(Long parentId);

    void delete(RatingReply ratingReply);

    void deleteAll(List<RatingReply> ratingReplies);

    void deleteAllByProjectRatingId(Long ratingId);
}
