package com.csv.csvproject.configuration.service;

import com.csv.csvproject.configuration.dto.FileRequestDto;
import com.csv.csvproject.configuration.dto.FileResponseDto;

import java.util.List;

public interface FileService {
    List<FileResponseDto> getAllFileDetails();
    FileResponseDto findById(int id);
    FileResponseDto addFileDetails(FileRequestDto fileDetails);
}
