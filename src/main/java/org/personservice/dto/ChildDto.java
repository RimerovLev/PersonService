package org.personservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class ChildDto extends PersonDto{
    String school;

    public ChildDto(Integer id, String name, LocalDate birthDate, AddressDto address, String school) {
        super(id, name, birthDate, address);
        this.school = school;
    }
}
