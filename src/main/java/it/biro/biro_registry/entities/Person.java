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
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String kId;
    private String name;
    private String surname;
    private String city;
    private String address;
    private boolean deleted;
    @ManyToMany(mappedBy="people")
    @JsonIgnore
    @ToString.Exclude
    private List<Company> companies;

}
