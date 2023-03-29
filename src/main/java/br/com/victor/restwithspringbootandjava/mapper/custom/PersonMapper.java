package br.com.victor.restwithspringbootandjava.mapper.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.victor.restwithspringbootandjava.data.vo.v2.PersonVOv2;
import br.com.victor.restwithspringbootandjava.model.Person;

@Service
public class PersonMapper {

    public PersonVOv2 convertEntityToVo(Person person) {
        PersonVOv2 vo = new PersonVOv2();
        vo.setId(person.getId());
        vo.setFirstName(person.getFirstName());
        vo.setLastName(person.getLastName());
        vo.setAddress(person.getAddress());
        vo.setGender(person.getGender());
        vo.setBirthDay(new Date());
        return vo;
    }

    public Person convertVoToEntity(PersonVOv2 person) {
        Person entity = new Person();
        entity.setId(person.getId());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        // vo.setBirthDay(new Date());
        return entity;
    }

}
