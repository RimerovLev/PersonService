package org.personservice.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.personservice.dao.PersonRepository;
import org.personservice.dto.AddressDto;
import org.personservice.dto.CityPopulationDto;
import org.personservice.dto.PersonDto;
import org.personservice.exceptions.PersonNotFoundException;
import org.personservice.model.Person;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonServiceAppl implements PersonService{
    final PersonRepository personRepository;
    final ModelMapper modelMapper;
    @Override
    public Boolean addPerson(PersonDto personDto) {
        if (!personRepository.findById(personDto.getId()).isPresent()){
            personRepository.save(modelMapper.map(personDto, Person.class));
            return true;
        }
        return false;
    }

    @Override
    public PersonDto getPersonById(Integer id) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        return modelMapper.map(person, PersonDto.class);
    }

    @Override
    public PersonDto deletePerson(Integer id) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        personRepository.deleteById(id);
        return modelMapper.map(person, PersonDto.class);
    }

    @Transactional(readOnly = true)
    @Override
    public List<PersonDto> getPersonsByCity(String city) {
        return personRepository.findAllByAddressCity(city)
                .map(person -> modelMapper.map(person, PersonDto.class)).toList();

    }

    @Transactional(readOnly = true)
    @Override
    public List<PersonDto> getPersonsByAge(Integer from, Integer to) {
        return personRepository.findByBirthDateBetween(
                        LocalDate.now().minusYears(to),
                        LocalDate.now().minusYears(from))
                .map(person -> modelMapper.map(person, PersonDto.class))
                .toList();
    }

    @Override
    public PersonDto updatePersonName(Integer id, String name) {
        return personRepository.findById(id)
                .map(person -> {
                    person.setName(name);
                    personRepository.save(person);
                    return modelMapper.map(person, PersonDto.class);
                })
                .orElseThrow(PersonNotFoundException::new);
    }

    @Transactional(readOnly = true)
    @Override
    public Iterable<PersonDto> getPersonsByName(String name) {
        return personRepository.findAllByNameIgnoreCase(name)
                .map(person -> modelMapper.map(person, PersonDto.class))
                .collect(Collectors.toList());

    }

    @Override
    public PersonDto updatePersonAddress(Integer id, AddressDto addressDto) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        person.setAddress(modelMapper.map(addressDto, person.getAddress().getClass()));
        personRepository.save(person);
        return modelMapper.map(person, PersonDto.class);
    }

    @Override
    public Iterable<CityPopulationDto> getCityPopulation() {

        return personRepository.getCityPopulation();
    }
}
