package org.personservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@EqualsAndHashCode( of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "person")
@Inheritance( strategy = InheritanceType.TABLE_PER_CLASS)
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
