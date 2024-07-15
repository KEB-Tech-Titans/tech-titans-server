package com.example.techtitansserver.domain.inspection.Dto;

import com.example.techtitansserver.domain.inspection.Domain.InspectionResult;
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

    private String originalFileName;

    private String url;

    private String rating;

    public static InspectionResultResponseDto toDto(InspectionResult inspectionResult) {
        return InspectionResultResponseDto.builder()
                .id(inspectionResult.getId())
                .originalFileName(inspectionResult.getOriginalFileName())
                .url(inspectionResult.getUrl())
                .rating(inspectionResult.getRating())
                .build();
    }

}
