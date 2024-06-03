package com.example.team_12_be.global.page;

import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

@Getter
public class CustomPageable<T> {

    private final boolean first;
    private final boolean last;
    private final boolean hasNext;
    private int totalPages;
    private long totalElements;
    private final int page;
    private final int size;

    public CustomPageable(Page<T> page) {
        this.first = page.isFirst();
        this.last = page.isLast();
        this.hasNext = page.hasNext();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.page = page.getNumber() + 1;
        this.size = page.getSize();
    }

    public CustomPageable(Slice<T> slice) {
        this.first = slice.isFirst();
        this.last = slice.isLast();
        this.hasNext = slice.hasNext();
        this.page = slice.getNumber() + 1;
        this.size = slice.getSize();
    }
}
