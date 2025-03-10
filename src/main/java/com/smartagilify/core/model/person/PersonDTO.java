package com.smartagilify.core.model.person;

import com.smartagilify.core.model.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ApiModel(description = "Details about person")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO extends BaseDTO {
    @ApiModelProperty(notes = "this is first name of person")
    private String firstName;
    @ApiModelProperty(notes = "this is last name of person")
    private String lastName;
}
