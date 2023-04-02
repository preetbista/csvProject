package com.csv.csvproject.inventory.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductRequestDto implements Serializable {
    private String name;
    private Long quantity;
    private String code;
    private Long price;
}
