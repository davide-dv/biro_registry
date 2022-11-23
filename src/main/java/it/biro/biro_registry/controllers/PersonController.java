package it.biro.biro_registry.controllers;

import it.biro.biro_registry.controllers.dto.CompanyPerson;
import it.biro.biro_registry.entities.Company;
import it.biro.biro_registry.entities.Person;
import it.biro.biro_registry.services.CompanyService;
import it.biro.biro_registry.services.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/registry/person")
public class PersonController {

    @Autowired
    private PersonService personService;
    @Autowired
    private CompanyService companyService;

    @GetMapping("/")
    public Mono<String> get() {
        return Mono.just("ok");
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Person> insertPerson(@RequestBody Person person) {
        log.info(person.toString());
        return Mono.just(personService.save(Person.builder()
                .kId(person.getKId())
                .name(person.getName())
                .surname(person.getSurname())
                .city(person.getCity())
                .address(person.getAddress()).build()));
    }

    @PutMapping("/add/company")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addToCompany(@RequestBody CompanyPerson companyPerson) {
        companyService.findById(companyPerson.getCompanyId()).ifPresentOrElse(company -> {
            personService.findById(companyPerson.getPersonId()).ifPresentOrElse(person -> {
                List<Company> companies = person.getCompanies();
                companies.add(company);
                person.setCompanies(companies);

                List<Person> people = company.getPeople();
                people.add(person);

                personService.save(person);
            }, () -> {
                throw new IllegalArgumentException(companyPerson.toString());
            });
        }, () -> {
            throw new IllegalArgumentException(companyPerson.toString());
        });
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> other(Exception exception){
        log.error(exception.getMessage());
        if (exception instanceof IllegalArgumentException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping("/name/{name}")
    public Flux<Person> getByName(@PathVariable String name) {
        return Mono.just(personService.findByName(name)).flatMapMany(Flux::fromIterable);
    }

    @GetMapping("/company/{id}")
    public Flux<Person> getByCompanyId(@PathVariable Long id) {
        return Mono.just(personService.findByCompanyId(id)).flatMapMany(Flux::fromIterable);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        personService.delete(id);
    }
}
