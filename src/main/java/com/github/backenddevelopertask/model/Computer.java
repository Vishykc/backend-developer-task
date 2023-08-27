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
    private Long cid; //computer id

    private String processor;

    private int cores;

    private String graphicsCard;



}
