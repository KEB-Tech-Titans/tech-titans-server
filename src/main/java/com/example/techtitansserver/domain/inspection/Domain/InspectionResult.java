package com.example.techtitansserver.domain.inspection.Domain;

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
public class InspectionResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String originalFileName;

    @Column(nullable = false, unique = true)
    private String savedFileName;

    @Column(nullable = false, unique = true)
    private String savedPath;

    @Column(nullable = false, unique = true)
    private String url;

    @Column(nullable = false)
    private Long fileSize;

    @Column(nullable = false)
    private String contentType;

    // 등급 종류 정해지면 Enum으로 수정 예정
    @Column(nullable = false)
    private String rating;

    // 등급 판정 기준 다 정해지면, 어느 부분에 어떤 종류의 결함이 있는지 관련 칼럼 추가

    public static InspectionResult create(
            String originalFileName, String savedFileName,
            String savedPath, String url, Long fileSize, String contentType, String rating) {
        return InspectionResult.builder()
                .originalFileName(originalFileName)
                .savedFileName(savedFileName)
                .savedPath(savedPath)
                .url(url)
                .fileSize(fileSize)
                .contentType(contentType)
                .rating(rating)
                .build();
    }

}
