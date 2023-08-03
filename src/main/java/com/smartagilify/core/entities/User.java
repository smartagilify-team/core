package com.smartagilify.core.entities;

import com.smartagilify.core.enumerations.EN_ROLE;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "CORE_USERS")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity {

    private String username;
    private String password;
    @ManyToOne
    @JoinColumn(name = "PERSON_ID", foreignKey = @ForeignKey(name = "FK_PERSON_USER"))
    private Person person;

    @Enumerated(EnumType.STRING)
    private EN_ROLE role;
    private Boolean isAccountNonExpired;
    private Boolean isAccountNonLocked;
    private Boolean isEnabled;

}
