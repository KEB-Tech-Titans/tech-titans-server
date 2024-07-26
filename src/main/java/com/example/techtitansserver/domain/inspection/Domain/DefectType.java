package com.example.techtitansserver.domain.inspection.Domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DefectType {

    OIL("오일"),
    SCRATCH("스크래치"),
    STAIN("찍힘"),
    BLACK_SPOT("흑점");

    private String defectName;
}
