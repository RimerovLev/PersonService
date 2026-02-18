package org.personservice.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@EqualsAndHashCode( of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "person")
public class Person  implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    Integer id;
    @Setter
    String name;
    LocalDate birthDate;
    @Setter
    @Embedded
    Address address;
}
