package com.smartagilify.core.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@RequiredArgsConstructor
@SuperBuilder
public class PageDTO<D extends BaseDTO> {
    private final long totalElements;
    private final int totalPages;
    private final List<D> results;
}
