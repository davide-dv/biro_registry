package it.biro.biro_registry.services;

import it.biro.biro_registry.entities.Company;
import it.biro.biro_registry.entities.Person;
import it.biro.biro_registry.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public Collection<Person> findByName(String name) {
        return personRepository.findByName(name);
    }

    public Collection<Person> findByCompanyId(Long id) {
        return personRepository.findByCompanies_Id(id);
    }

    public Optional<Person> findById(Long id) {
        return personRepository.findById(id);
    }

    public void delete(Long id) {
        personRepository.findById(id).ifPresent(person -> {
            person.setDeleted(true);
            personRepository.save(person);
        });
    }
}
