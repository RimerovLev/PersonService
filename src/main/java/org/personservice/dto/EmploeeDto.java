package org.personservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@NoArgsConstructor
@Getter
public class EmploeeDto extends PersonDto{
    String company;
    int salary;
}
