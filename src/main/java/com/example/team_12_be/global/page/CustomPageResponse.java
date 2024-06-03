package com.example.team_12_be.global.page;


import lombok.Getter;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.SliceImpl;

import java.util.List;

@Getter
public class CustomPageResponse<T> {

    private final List<T> content;

    private final CustomPageable<T> customPageable;

    public CustomPageResponse(List<T> content, Pageable pageable, long total) {
        this.content = content;
        this.customPageable = new CustomPageable<>(new PageImpl<>(content, pageable, total));
    }

    public CustomPageResponse(List<T> content, Pageable pageable, boolean hasNext) {
        this.content = content;
        this.customPageable = new CustomPageable<>(new SliceImpl<>(content, pageable, hasNext));
    }
}