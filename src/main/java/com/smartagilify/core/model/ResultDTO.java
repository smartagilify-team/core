package com.smartagilify.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultDTO<D extends BaseDTO> {
    private List<D> resultList;
    private Integer pageNumber;
    private Integer pageSize;
    private Integer totalPages;
    private Long totalRecordSize;
    private Integer code;
    private String message;
}
