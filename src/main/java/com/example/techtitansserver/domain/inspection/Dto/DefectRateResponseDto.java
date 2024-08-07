package com.example.techtitansserver.domain.inspection.Dto;

import java.time.LocalDateTime;
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
public class DefectRateResponseDto {

    private Float defectRate;
    private String date;

    public static DefectRateResponseDto toDto(Float defectRate, String date) {
        return DefectRateResponseDto.builder()
                .defectRate(defectRate)
                .date(date)
                .build();
    }

}
