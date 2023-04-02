package com.csv.csvproject.configuration.dto;

import com.csv.csvproject.inventory.model.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class FileRequestDto implements Serializable {
    private String filePath;
}
