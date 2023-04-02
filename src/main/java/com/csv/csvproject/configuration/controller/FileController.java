package com.csv.csvproject.configuration.controller;

import com.csv.csvproject.configuration.dto.FileRequestDto;
import com.csv.csvproject.configuration.dto.FileResponseDto;
import com.csv.csvproject.configuration.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/files")
public class FileController {
    @Autowired
    private FileService fileService;

    @GetMapping
    public List<FileResponseDto> getAllFileDetails() {
        return fileService.getAllFileDetails();
    }
    @GetMapping("/{id}")
    public FileResponseDto findById(@PathVariable int id) {
        return fileService.findById(id);
    }

    @PostMapping
    public FileResponseDto addFileDetails(@RequestBody FileRequestDto fileRequestDto) {
        return fileService.addFileDetails(fileRequestDto);
    }
}
