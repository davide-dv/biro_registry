package it.biro.biro_registry.repositories;

import it.biro.biro_registry.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Collection<Person> findByName(String name);

    Collection<Person> findByCompanies_Id(Long id);
}
