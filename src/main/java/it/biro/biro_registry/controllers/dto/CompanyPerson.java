package it.biro.biro_registry.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanyPerson {
    private Long companyId;
    private Long personId;
}
