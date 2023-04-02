package com.csv.csvproject.configuration.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "file_details")
@Getter
@Setter
public class FileDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "file_path")
    private String filePath;
    @Column(name = "success_count")
    private int successCount;
    @Column(name = "fail_count")
    private int failCount;
    @Enumerated(EnumType.STRING)
    private Status status;

}
