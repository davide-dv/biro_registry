package it.biro.biro_registry.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String city;
    private String vat;
    private boolean deleted;
    @ManyToMany
    @JoinTable(
            name = "company_person",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    @JsonIgnore
    @ToString.Exclude
    private List<Person> people;

}
