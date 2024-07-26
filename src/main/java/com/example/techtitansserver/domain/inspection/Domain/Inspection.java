package com.example.techtitansserver.domain.inspection.Domain;

import com.example.techtitansserver.global.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(indexes = {@Index(name = "analyzed_file_name_index", columnList = "analyzedFileName")})
public class Inspection extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String analyzedFileName;

    @Column(nullable = false)
    private DefectType defectType;

    @Column(nullable = false)
    private Float area;

}
