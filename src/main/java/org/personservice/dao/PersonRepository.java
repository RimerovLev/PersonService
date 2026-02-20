package org.personservice.dao;

import org.personservice.dto.ChildDto;
import org.personservice.dto.CityPopulationDto;
import org.personservice.dto.EmploeeDto;
import org.personservice.dto.PersonDto;
import org.personservice.model.Child;
import org.personservice.model.Emploee;
import org.personservice.model.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;


public interface PersonRepository extends CrudRepository <Person, Integer>{
    Stream<Person> findAllByAddressCity(String city);

    Stream<Person> findByBirthDateBetween(LocalDate from, LocalDate to);

    Stream<Person> findAllByNameIgnoreCase(String name);

    @Query("""
    select new org.personservice.dto.CityPopulationDto(p.address.city,count(p))
        from Person p
        group by p.address.city""")
    List<CityPopulationDto> getCityPopulation();

    Stream<Child> findAllChildrenBy();

    Stream<Emploee> findEmploeesBySalaryBetween(Integer from, Integer to);
}
