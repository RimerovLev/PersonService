package org.personservice.service;

import org.personservice.dto.AddressDto;
import org.personservice.dto.CityPopulationDto;
import org.personservice.dto.PersonDto;

import java.util.List;

public interface PersonService {
    Boolean addPerson(PersonDto personDto);
    PersonDto getPersonById(Integer id);
    PersonDto deletePerson(Integer id);
    List<PersonDto> getPersonsByCity(String city);
    List<PersonDto> getPersonsByAge(Integer from, Integer to);
    PersonDto updatePersonName(Integer id, String name);
    Iterable<PersonDto> getPersonsByName(String name);
    PersonDto updatePersonAddress(Integer id, AddressDto addressDto);
    Iterable<CityPopulationDto> getCityPopulation();
}
