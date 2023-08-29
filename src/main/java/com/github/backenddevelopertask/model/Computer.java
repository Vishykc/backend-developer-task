package com.github.backenddevelopertask.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name="computers")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Computer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String processor;

    @Column(nullable = false)
    private Integer cores;

    @Column(nullable = false)
    private String graphicsCard;
}
