package com.example.techtitansserver.domain.inspection.Dto;

import com.example.techtitansserver.domain.inspection.Domain.Inspection;
import java.time.LocalDateTime;
import java.util.List;
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
public class InspectionDetailResponseDto {

    private String analyzedFileName;

    private String analyzedImagePath;

    private Integer defectSeverity;

    private String inspectionTime;

    private List<NumberOfDefectiveResponseDto> numberOfDefectiveResponseDtoList;

    public static InspectionDetailResponseDto toDto(String analyzedFileName, String analyzedImagePath, List<NumberOfDefectiveResponseDto> numberOfDefectiveResponseDtoList, Integer defectSeverity, String inspectionTime) {
        return InspectionDetailResponseDto.builder()
                .analyzedFileName(analyzedFileName)
                .analyzedImagePath(analyzedImagePath)
                .numberOfDefectiveResponseDtoList(numberOfDefectiveResponseDtoList)
                .defectSeverity(defectSeverity)
                .inspectionTime(inspectionTime)
                .build();
    }

}
