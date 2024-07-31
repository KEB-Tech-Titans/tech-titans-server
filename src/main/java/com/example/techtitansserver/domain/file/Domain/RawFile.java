package com.example.techtitansserver.domain.file.Domain;

import com.example.techtitansserver.global.common.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class RawFile extends BaseEntity {

    @Id
    @Column(nullable = false, unique = true)
    private String savedFileName;

    @Column(nullable = false, unique = true)
    private String savedPath;

    @Column(nullable = false)
    private Long fileSize;

    @Column(nullable = false)
    private String contentType;
}
