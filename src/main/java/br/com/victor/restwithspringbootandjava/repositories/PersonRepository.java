package br.com.victor.restwithspringbootandjava.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.victor.restwithspringbootandjava.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
