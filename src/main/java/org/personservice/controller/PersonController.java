package org.personservice.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.personservice.dto.AddressDto;
import org.personservice.dto.CityPopulationDto;
import org.personservice.dto.PersonDto;
import org.personservice.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {
    private  final PersonService personService;

    @PostMapping
    public Boolean addPerson(@RequestBody PersonDto personDto) {
        return personService.addPerson(personDto);
    }

    @GetMapping("/{id}")
    public PersonDto getPersonById(@PathVariable Integer id) {
        return personService.getPersonById(id);
    }

    @GetMapping("/city/{city}")
    public List<PersonDto> getPersonsByCity(@PathVariable String city) {
        return personService.getPersonsByCity(city);
    }

    @GetMapping("/ages/{from}/{to}")
    public List<PersonDto> getPersonsByAge(@PathVariable Integer from, @PathVariable Integer to) {
        return personService.getPersonsByAge(from, to);
    }

    @PutMapping("/{id}/name/{name}")
    public PersonDto updatePersonName(@PathVariable Integer id, @PathVariable String name) {
        return personService.updatePersonName(id, name);
    }

    @GetMapping("/name/{name}")
    public Iterable<PersonDto> getPersonsByName(@PathVariable String name) {
        return personService.getPersonsByName(name);
    }

    @PutMapping("/{id}/address")
    public PersonDto updatePersonAddress(@PathVariable Integer id, @RequestBody AddressDto addressDto) {
        return personService.updatePersonAddress(id, addressDto);
    }

    @DeleteMapping("{id}")
    public PersonDto deletePerson(@PathVariable Integer id) {
        return personService.deletePerson(id);
    }

    @GetMapping("/population/city")
    public Iterable<CityPopulationDto> getCityPopulation() {
        return personService.getCityPopulation();
    }
}
