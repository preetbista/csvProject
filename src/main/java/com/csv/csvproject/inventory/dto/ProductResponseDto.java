package com.csv.csvproject.inventory.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ProductResponseDto implements Serializable {
    private String name;
    private Long quantity;
    private String code;
    private Long price;
}
