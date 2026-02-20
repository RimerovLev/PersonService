package org.personservice.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder; // Add this import for ordering
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"id", "name", "birthDate", "address"}) // Enforce this exact field orde
@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ChildDto.class, name = "child"),
        @JsonSubTypes.Type(value = EmploeeDto.class, name = "employee"),
        @JsonSubTypes.Type(value = PersonDto.class, name = "person")
})
public class PersonDto {
    Integer id;
    String name;
    LocalDate birthDate;
    AddressDto address; // Ensure it's named 'address' to match the desired JSON key
}
