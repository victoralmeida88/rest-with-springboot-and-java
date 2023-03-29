package br.com.victor.restwithspringbootandjava.services;

import java.util.List;
import java.util.logging.Logger;

import br.com.victor.restwithspringbootandjava.exceptions.RequiredObjectIsNullException;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import br.com.victor.restwithspringbootandjava.controllers.PersonController;
import br.com.victor.restwithspringbootandjava.data.vo.v1.PersonVO;
import br.com.victor.restwithspringbootandjava.data.vo.v2.PersonVOv2;
import br.com.victor.restwithspringbootandjava.exceptions.ResourceNotFoundException;
import br.com.victor.restwithspringbootandjava.mapper.DozzerMapper;
import br.com.victor.restwithspringbootandjava.mapper.custom.PersonMapper;
import br.com.victor.restwithspringbootandjava.model.Person;
import br.com.victor.restwithspringbootandjava.repositories.PersonRepository;

@Service
public class PersonServices {

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper mapper;

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public PersonVO findById(Long id) {

        logger.info("Find one person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        var vo = DozzerMapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;
    }

    public List<PersonVO> findAll() {

        logger.info("Find all people!");

        var persons = DozzerMapper.parseListObjects(repository.findAll(), PersonVO.class);
        persons.stream()
                .forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
        return persons;

    }

    public PersonVO create(PersonVO person) {


        if (person == null) throw new RequiredObjectIsNullException();

        logger.info("Creating one person!");

        var entity = DozzerMapper.parseObject(person, Person.class);

        var vo = DozzerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public PersonVOv2 createV2(PersonVOv2 person) {

        logger.info("Creating one person with V2!");

        var entity = mapper.convertVoToEntity(person);

        var vo = mapper.convertEntityToVo(repository.save(entity));
        return vo;
    }

    public PersonVO update(PersonVO person) {

        if (person == null) throw new RequiredObjectIsNullException();

        logger.info("Updating one person!");

        Person entity = repository.findById(person.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var vo = DozzerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;

    }

    public void delete(Long id) {

        logger.info("Deleting one person!");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        repository.delete(entity);

    }

}
