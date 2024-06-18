package com.example.team_12_be.global.presentation;

public record CustomPageRequest(
        int page,
        int size,
        int limit
) {
    private static final int LIMIT_CONSTRAINT = 100;

    private static final int DEFAULT_PAGE_SIZE = 8;

    @Override
    public int page(){
        if (page < 1) {
            return 0;
        }

        return page - 1;
    }

    @Override
    public int size(){
        if (limit > LIMIT_CONSTRAINT){
            return Math.min(size, LIMIT_CONSTRAINT);
        }

        if (size < 1){
            return DEFAULT_PAGE_SIZE;
        }

        return Math.min(size, limit);
    }
}