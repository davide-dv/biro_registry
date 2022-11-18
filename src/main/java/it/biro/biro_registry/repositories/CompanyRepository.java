package it.biro.biro_registry.repositories;

import it.biro.biro_registry.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Collection<Company> findByName(String name);

    Collection<Company> findByPeople_Id(Long id);
}
