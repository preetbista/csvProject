package com.csv.csvproject.configuration.service.impl;

import com.csv.csvproject.configuration.dto.FileRequestDto;
import com.csv.csvproject.configuration.dto.FileResponseDto;
import com.csv.csvproject.configuration.model.FileDetails;
import com.csv.csvproject.configuration.model.Status;
import com.csv.csvproject.configuration.repository.FileRepository;
import com.csv.csvproject.configuration.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileRepository fileRepository;
    @Override
    public List<FileResponseDto> getAllFileDetails() {
        List<FileResponseDto> fileResponseDtos = new ArrayList<>();
        List<FileDetails> fileDetailsList = fileRepository.findAll();

        for(FileDetails fileDetails:fileDetailsList){
            FileResponseDto fileResponseDto = new FileResponseDto();
            fileResponseDto.setFilePath(fileDetails.getFilePath());
            fileResponseDtos.add(fileResponseDto);
        }
        return fileResponseDtos;
    }

    @Override
    public FileResponseDto findById(int id) {
        FileResponseDto fileResponseDto = new FileResponseDto();
        FileDetails retrievedFile = fileRepository.findById(id).orElseThrow(null);
        if(retrievedFile != null){
            fileResponseDto.setFilePath(retrievedFile.getFilePath());
        }else{
            fileResponseDto.setFilePath(null);
        }
        return fileResponseDto;
    }

    @Override
    public FileResponseDto addFileDetails(FileRequestDto fileRequestDto) {
        FileDetails fileDetails = new FileDetails();
        fileDetails.setFilePath(fileRequestDto.getFilePath());
        fileDetails.setStatus(Status.PENDING);
        FileDetails save = fileRepository.save(fileDetails);

        FileResponseDto fileResponseDto = new FileResponseDto();
        fileResponseDto.setFilePath(save.getFilePath());
        return fileResponseDto;
    }

}
