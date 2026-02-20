package org.personservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@NoArgsConstructor
@Getter
public class EmploeeDto extends PersonDto{
    String company;
    int salary;

    public EmploeeDto(Integer id, String name, LocalDate birthDate, AddressDto address, String company, int salary) {
        super(id, name, birthDate, address);
        this.company = company;
        this.salary = salary;
    }
}
