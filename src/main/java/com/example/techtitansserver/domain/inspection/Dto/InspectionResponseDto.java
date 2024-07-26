package com.example.techtitansserver.domain.inspection.Dto;

import com.example.techtitansserver.domain.inspection.Domain.DefectType;
import com.example.techtitansserver.domain.inspection.Domain.Inspection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class InspectionResponseDto {

    private Long id;

    private String analyzedFileName;

    private DefectType defectType;

    private Float area;

    public static InspectionResponseDto toDto(Inspection inspection) {
        return InspectionResponseDto.builder()
                .id(inspection.getId())
                .analyzedFileName(inspection.getAnalyzedFileName())
                .defectType(inspection.getDefectType())
                .area(inspection.getArea())
                .build();
    }
}
