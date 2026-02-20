package org.personservice.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Child extends Person{
    private static final long serialVersionUID = 1L;
    String school;

    public Child(Integer id, String name, LocalDate birthDate, Address address, String school) {
        super(id, name, birthDate, address);
        this.school = school;
    }
}
