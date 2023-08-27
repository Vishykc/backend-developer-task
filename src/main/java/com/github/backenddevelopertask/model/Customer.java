package com.github.backenddevelopertask.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="customers")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String address;

    @OneToMany(targetEntity = Computer.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "cc_fk", referencedColumnName = "id") //customer computer foreign key
    private List<Computer> computers;

}
