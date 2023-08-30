package com.github.backenddevelopertask.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Computer computer = (Computer) o;
        return Objects.equals(id, computer.id) && Objects.equals(processor, computer.processor) &&
                Objects.equals(cores, computer.cores) && Objects.equals(graphicsCard, computer.graphicsCard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, processor, cores, graphicsCard);
    }
}
