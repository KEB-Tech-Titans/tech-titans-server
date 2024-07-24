package com.example.techtitansserver.domain.inspection.Dto;

import com.example.techtitansserver.domain.inspection.Domain.Defect;
import com.example.techtitansserver.domain.inspection.Domain.InspectionResult;
import com.fasterxml.jackson.databind.JsonNode;
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
public class InspectionResultResponseDto {

    private Long id;

    private String savedFileName;

    private Defect defect;

    private Float area;

    public static InspectionResultResponseDto toDto(InspectionResult inspectionResult) {
        return InspectionResultResponseDto.builder()
                .id(inspectionResult.getId())
                .savedFileName(inspectionResult.getSavedFileName())
                .defect(inspectionResult.getDefect())
                .area(inspectionResult.getArea())
                .build();
    }
}
