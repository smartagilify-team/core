package com.smartagilify.core.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "CORE_PERSON")
public class Person extends BaseEntity {

    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "person")
    private Set<User> user;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
