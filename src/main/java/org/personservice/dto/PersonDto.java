package org.personservice.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder; // Add this import for ordering
import lombok.Getter;
import java.time.LocalDate;

@Getter
@JsonPropertyOrder({"id", "name", "birthDate", "address"}) // Enforce this exact field order in JSON
public class PersonDto {
    Integer id;
    String name;
    LocalDate birthDate;
    AddressDto address; // Ensure it's named 'address' to match the desired JSON key
}
