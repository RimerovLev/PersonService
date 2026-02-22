package org.personservice.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.personservice.dto.PersonDto;
import org.personservice.exceptions.UnknownPersonTypeException;
import org.personservice.model.Person;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class PersonModelDtoMapper {
    private static final String MODEL_PACKAGE = "org.personservice.model.";
    private static final String DTO_SUFFIX = "Dto";
    private static final String DTO_PACKAGE = "org.personservice.dto.";
    final ModelMapper modelMapper;

    public Person mapToModel(PersonDto personDto){

        return modelMapper.map(personDto, getModelClass(personDto));
    }

    public PersonDto mapToDto(Person person){

        return modelMapper.map(person, getDtoClass(person));
    }

    private Class<? extends PersonDto> getDtoClass(Person person) {
        String dtoClassName = person.getClass().getSimpleName() + DTO_SUFFIX;
        try{
            Class<? extends PersonDto> clazz = (Class<? extends PersonDto>) Class.forName(DTO_PACKAGE+ dtoClassName);
            return clazz;
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            throw new UnknownPersonTypeException("Unknown person type:" + person.getClass().getSimpleName());
        }
    }
@SuppressWarnings("unchecked")
    private Class<? extends Person> getModelClass(PersonDto personDto) {
        String modelClassName = personDto.getClass().getSimpleName();
        modelClassName = modelClassName.substring(0, modelClassName.length() - 3);
        try{
            Class<? extends Person> clazz = (Class<? extends Person>) Class.forName(MODEL_PACKAGE+ modelClassName);
            return clazz;
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            throw new UnknownPersonTypeException("Unknown person type:" + personDto.getClass().getSimpleName());
        }
    }
}
