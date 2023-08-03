package com.smartagilify.core.model;

import com.smartagilify.core.enumerations.EN_STATE;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public abstract class BaseDTO {
    protected Long id;
    protected String hashCode;
    protected String ip;
    protected Long version;
    protected EN_STATE state;
    protected Integer rowLevelId;
    protected Integer priority;
}
