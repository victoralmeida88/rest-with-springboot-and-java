package br.com.victor.restwithspringbootandjava.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.victor.restwithspringbootandjava.exceptions.ResourceNotFoundException;
import br.com.victor.restwithspringbootandjava.model.Person;
import br.com.victor.restwithspringbootandjava.repositories.PersonRepository;

@Service
public class PersonServices {

    @Autowired
    PersonRepository repository;

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public Person findById(Long id) {

        logger.info("Find one person!");

        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
    }

    public List<Person> findAll() {

        logger.info("Find all people!");

        return repository.findAll();

    }

    public Person create(Person person) {

        logger.info("Creating one person!");

        return repository.save(person);
    }

    public Person update(Person person) {

        logger.info("Updating one person!");

        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return repository.save(entity);

    }

    public void delete(Long id) {

        logger.info("Deleting one person!");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        repository.delete(entity);

    }

}
