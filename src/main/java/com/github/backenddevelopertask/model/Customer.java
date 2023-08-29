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

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String address;

    @OneToMany(targetEntity = Computer.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private List<Computer> computers;

}
