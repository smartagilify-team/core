package com.smartagilify.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InputFilter {
    private Integer pageNumber;
    private Integer pageSize;
    Sort.Direction direction;
    private String where;
    private String orderBy;
    private String groupBy;
    private String having;
    private Boolean fetchDeleted;
}
