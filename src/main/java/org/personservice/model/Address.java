package org.personservice.model;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address implements Serializable {
    private static final long serialVersionUID = 1L;
    String city;
    String street;
    Integer building;
}
