package com.example.techtitansserver.domain.inspection.Dto;

import com.example.techtitansserver.domain.inspection.Domain.DefectType;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.util.stream.Stream;
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
public class NumberOfDefectiveResponseDto {

    private DefectType defectType;

    private Long number;

    private String date;
}
