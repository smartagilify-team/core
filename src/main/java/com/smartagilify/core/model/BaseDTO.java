package com.smartagilify.core.model;

import com.smartagilify.core.enumerations.EN_STATE;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@ApiModel(description = "This is a common data between all of models.")
@SuperBuilder
public abstract class BaseDTO {
    @ApiModelProperty(notes = "this is a unique id for model")
    protected Long id;
    @ApiModelProperty(notes = "this is a hash code for id")
    protected Long hashCode;
    @ApiModelProperty(notes = "this is machine's ip")
    protected String ip;
    @ApiModelProperty(notes = "this is for handling optimistic locking")
    protected Long version;
    @ApiModelProperty(notes = "this is for handling state of data. e.g. deleted,updated and ...")
    protected EN_STATE state;
    @ApiModelProperty(notes = "this is row level")
    protected Integer rowLevelId;
    @ApiModelProperty(notes = "this is priority")
    protected Integer priority;
}
