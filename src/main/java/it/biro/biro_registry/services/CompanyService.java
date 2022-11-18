package it.biro.biro_registry.services;

import it.biro.biro_registry.entities.Company;
import it.biro.biro_registry.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public Company save(Company company) {
        return companyRepository.save(company);
    }

    public Collection<Company> findByName(String name) {
        return companyRepository.findByName(name);
    }

    public Collection<Company> findByPeopleId(Long id) {
        return companyRepository.findByPeople_Id(id);
    }

    public void delete(Long id) {
        companyRepository.findById(id).ifPresent(company -> {
            company.setDeleted(true);
            companyRepository.save(company);
        });
    }

    public Optional<Company> findById(Long id) {
        return companyRepository.findById(id);
    }

}
