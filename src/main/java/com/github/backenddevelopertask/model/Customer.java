package com.github.backenddevelopertask.model;

import jakarta.persistence.*;
import lombok.*;

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


}
