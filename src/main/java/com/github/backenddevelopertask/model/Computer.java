package com.github.backenddevelopertask.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name="computers")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Computer {

    // Primary key for the Computer entity, it is automatically generated
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Processor details for the computer
    @Column(nullable = false)
    private String processor;

    // Number of CPU cores in the computer
    @Column(nullable = false)
    private Integer cores;

    // Graphics card details for the computer
    @Column(nullable = false)
    private String graphicsCard;
}