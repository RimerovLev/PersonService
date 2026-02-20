package org.personservice.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.personservice.dao.PersonRepository;
import org.personservice.dto.*;
import org.personservice.exceptions.PersonNotFoundException;
import org.personservice.model.Address;
import org.personservice.model.Child;
import org.personservice.model.Emploee;
import org.personservice.model.Person;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonServiceAppl implements PersonService, CommandLineRunner {


    final PersonRepository personRepository;
    final ModelMapper modelMapper;
    @Override
    public Boolean addPerson(PersonDto personDto) {
        if (personDto.getId() != null && personRepository.findById(personDto.getId()).isPresent()) {
            return false;
        }if(personDto instanceof ChildDto){
            personRepository.save(modelMapper.map(personDto, Child.class));
            return true;
        }if(personDto instanceof EmploeeDto){
            personRepository.save(modelMapper.map(personDto, Emploee.class));
            return true;
        }if(personDto instanceof PersonDto){
            personRepository.save(modelMapper.map(personDto, Person.class));
            return true;
        }
        return false;
    }

    @Override
    public PersonDto getPersonById(Integer id) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        if(person instanceof Child){
            return modelMapper.map(person, ChildDto.class);
        }
        if(person instanceof Emploee){
            return modelMapper.map(person, EmploeeDto.class);
        }
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

    @Transactional(readOnly = true)
    @Override
    public Iterable<ChildDto> findAllChildren() {
        return personRepository.findAllChildrenBy()
                .map(p -> modelMapper.map(p, ChildDto.class))
                .collect(Collectors.toList());

    }

    @Transactional(readOnly = true)
    @Override
    public Iterable<EmploeeDto> getEmploeesBySalary(Integer from, Integer to) {
        return personRepository.findEmploeesBySalaryBetween(from, to)
                .map(p -> modelMapper.map(p, EmploeeDto.class))
                .collect(Collectors.toList());
    }


    @Transactional
    @Override
    public void run(String... args) throws Exception {
        if (personRepository.count()==0){
            personRepository.save(new Person(1, "John Doe", LocalDate.of(1990, 1, 1),
                    new Address("123 Main St", "Anytown", 2)));
            personRepository.save(new Person(2, "Leo Rim", LocalDate.of(1989, 1, 1),
                    new Address("456 Main St", "Anytown", 2)));
            personRepository.save(new Child(3, "Child Doe", LocalDate.of(2000, 1, 1),
                    new Address("789 Main St", "Anytown", 2), "Any School"));
            personRepository.save(new Emploee(4, "Emploee Doe", LocalDate.of(2000, 1, 1),
                    new Address("789 Main St", "Anytown", 2), "Any Company", 10000)
            );
        }
    }

}
