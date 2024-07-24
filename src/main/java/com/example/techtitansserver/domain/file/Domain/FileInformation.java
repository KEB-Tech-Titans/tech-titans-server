package com.example.techtitansserver.domain.file.Domain;

import com.example.techtitansserver.domain.inspection.Domain.InspectionResult;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class FileInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String savedFileName;

    @Column(nullable = false, unique = true)
    private String savedPath;

    @Column(nullable = false)
    private Long fileSize;

    @Column(nullable = false)
    private String contentType;
}
