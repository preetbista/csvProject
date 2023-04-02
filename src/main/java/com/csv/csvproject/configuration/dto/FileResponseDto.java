package com.csv.csvproject.configuration.dto;

import com.csv.csvproject.configuration.model.Status;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class FileResponseDto implements Serializable {
    private String filePath;
    private Status status;

}
