package com.csv.csvproject.configuration.repository;

import com.csv.csvproject.configuration.model.FileDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileDetails, Integer> {
}
