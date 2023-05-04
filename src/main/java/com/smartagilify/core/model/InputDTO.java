package com.smartagilify.core.model;

import com.smartagilify.core.enumerations.EN_ACTION_TYPE;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InputDTO<D extends BaseDTO> {
    private EN_ACTION_TYPE actionType;
    private Long fieldId;
    private String hashCode;
    private Long userId;
    private String session;
    private D data;
    private InputFilter inputFilter;
}
