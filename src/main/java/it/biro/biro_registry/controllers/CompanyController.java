package it.biro.biro_registry.controllers;

import it.biro.biro_registry.entities.Company;
import it.biro.biro_registry.services.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/api/registry/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/")
    public Mono<String> get() {
        return Mono.just("ok");
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Company> insertCompany(@RequestBody Company company) {
        log.info(company.toString());
        return Mono.just(companyService.save(Company.builder()
                .name(company.getName())
                .city(company.getCity())
                .vat(company.getVat()).build()));
    }

    @GetMapping("/name/{name}")
    public Flux<Company> getByName(@PathVariable String name) {
        return Mono.just(companyService.findByName(name)).flatMapMany(Flux::fromIterable);
    }

    @GetMapping("/person/{id}")
    public Flux<Company> getByPersonId(@PathVariable Long id) {
        return Mono.just(companyService.findByPeopleId(id)).flatMapMany(Flux::fromIterable);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        companyService.delete(id);
    }

}