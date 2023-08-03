package com.smartagilify.core.model.person;

import com.smartagilify.core.model.BaseDTO;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO extends BaseDTO {
    @NotNull
    private String firstName;
    private String lastName;
}
