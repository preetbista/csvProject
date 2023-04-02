package com.csv.csvproject.inventory.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String code;
    private Long quantity;
    private Long price;
    @Enumerated(EnumType.STRING)
    private Status status;
}
