package com.example.techtitansserver.domain.inspection.Service;

import com.example.techtitansserver.domain.file.Domain.AnalyzedFile;
import com.example.techtitansserver.domain.file.Service.FileService;
import com.example.techtitansserver.domain.inspection.Dao.InspectionRepository;
import com.example.techtitansserver.domain.inspection.Domain.DefectType;
import com.example.techtitansserver.domain.inspection.Domain.Inspection;
import com.example.techtitansserver.domain.inspection.Dto.InspectionDetailResponseDto;
import com.example.techtitansserver.domain.inspection.Dto.NumberOfDefectiveResponseDto;
import com.example.techtitansserver.global.common.dto.PagedResponseDto;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class InspectionService {

    private final InspectionRepository inspectionRepository;
    private final FileService fileService;

    public List<NumberOfDefectiveResponseDto> getNumberOfDefectByType(Integer year, Integer month, Integer date) {
        List<NumberOfDefectiveResponseDto> numberOfDefectiveResponseDtoList = new ArrayList<>();
        for (DefectType defectType : DefectType.values()) {
            Long numberOfDefect;
            if (year == null)
                numberOfDefect = inspectionRepository.countDefectByType(defectType);
            else if (month == null)
                numberOfDefect = inspectionRepository.countDefectByTypeAndYear(defectType, year);
            else if (date == null)
                numberOfDefect = inspectionRepository.countDefectByTypeAndMonth(defectType, year, month);
            else
                numberOfDefect = inspectionRepository.countDefectByTypeAndDate(defectType, year, month, date);

            NumberOfDefectiveResponseDto numberOfDefectiveResponseDto = makeNumberOfDefectiveResponseDto(defectType, numberOfDefect, null);
            numberOfDefectiveResponseDtoList.add(numberOfDefectiveResponseDto);
        }
        return numberOfDefectiveResponseDtoList;
    }

    public List<NumberOfDefectiveResponseDto> getNumberOfDefectByTypeAndDate(Integer year, Integer month, Integer date) {
        List<NumberOfDefectiveResponseDto> numberOfDefectiveResponseDtoList = new ArrayList<>();
        for (DefectType defectType : DefectType.values()) {
            if (year == null) {
                for (int i = 1992; i <= LocalDate.now().getYear(); i++) {
                    Long numberOfDefect = inspectionRepository.countDefectByTypeAndYear(defectType, i);
                    NumberOfDefectiveResponseDto numberOfDefectiveResponseDto = makeNumberOfDefectiveResponseDto(defectType, numberOfDefect, Integer.toString(i));
                    numberOfDefectiveResponseDtoList.add(numberOfDefectiveResponseDto);
                }
            } else if (month == null) {
                for (int i = 1; i <= 12; i++) {
                    Long numberOfDefect = inspectionRepository.countDefectByTypeAndMonth(defectType, year, i);
                    NumberOfDefectiveResponseDto numberOfDefectiveResponseDto = makeNumberOfDefectiveResponseDto(defectType, numberOfDefect, year + "-" + i);
                    numberOfDefectiveResponseDtoList.add(numberOfDefectiveResponseDto);
                }
            } else {
                for (int i = 1; i <= YearMonth.of(year, month).lengthOfMonth(); i++) {
                    Long numberOfDefect = inspectionRepository.countDefectByTypeAndDate(defectType, year, month, i);
                    NumberOfDefectiveResponseDto numberOfDefectiveResponseDto = makeNumberOfDefectiveResponseDto(defectType, numberOfDefect, year + "-" + month + "-" + i);
                    numberOfDefectiveResponseDtoList.add(numberOfDefectiveResponseDto);
                }
            }
        }
        return numberOfDefectiveResponseDtoList;
    }

    public Long countAnalyzedFile(Integer year, Integer month, Integer date) {
        return fileService.countAnalyzedFile(year, month, date);
    }

   public Float getDefectRate(Integer year, Integer month, Integer date) {
         long numberOfAnalyzedFile = countAnalyzedFile(year, month, date);
         long numberOfDefective = fileService.countFilesByIsPassed(year, month, date, Boolean.FALSE);
         return numberOfAnalyzedFile == 0 ? 0 : numberOfDefective / (float) numberOfAnalyzedFile * 100;
    }

    public PagedResponseDto<InspectionDetailResponseDto> getDetails(LocalDate startDate, LocalDate endDate, DefectType defectType, Integer limit, Integer offset) {
        Pageable pageable = PageRequest.of(offset, limit, Sort.Direction.ASC, "createdAt");
        Page<Inspection> inspections = inspectionRepository.getInspectionsByDefectTypeAndDuration(defectType, startDate, endDate, pageable);

        log.info(inspections.getContent().size() + "개");
        List<InspectionDetailResponseDto> inspectionDetailResponseDtoList = new ArrayList<>();
        for (Inspection inspection : inspections.getContent()) {
            String analyzedFileName = inspection.getAnalyzedFileName();
            log.info(analyzedFileName);
            List<NumberOfDefectiveResponseDto> numberOfDefectiveResponseDtoList = new ArrayList<>();
            for (DefectType type : DefectType.values()) {
                Long numberOfDefect = inspectionRepository.getInspectionsByDefectTypeAndAnalyzedFileName(type, analyzedFileName);
                NumberOfDefectiveResponseDto numberOfDefectiveResponseDto = makeNumberOfDefectiveResponseDto(type, numberOfDefect, null);
                numberOfDefectiveResponseDtoList.add(numberOfDefectiveResponseDto);
            }
            AnalyzedFile analyzedFile = fileService.getAnalyzedFileById(analyzedFileName);
            String imagePath = analyzedFile.getSavedPath();
            Integer defectSeverity = analyzedFile.getDefectSeverity();
            inspectionDetailResponseDtoList.add(InspectionDetailResponseDto.toDto(analyzedFileName, imagePath, numberOfDefectiveResponseDtoList, defectSeverity, inspection.getCreatedAt().toString()));
        }
        return new PagedResponseDto<>(inspectionDetailResponseDtoList, inspections.getNumber(),
                inspections.getSize(), inspections.getTotalElements(), inspections.getTotalPages(),
                inspections.hasPrevious(), inspections.hasNext());
    }

    public Long countBadSmartphones(Integer year, Integer month, Integer date) {
        return fileService.countFilesByIsPassed(year, month, date, false);
    }

    public NumberOfDefectiveResponseDto makeNumberOfDefectiveResponseDto(DefectType defectType, Long numberOfDefect, String date) {
        return NumberOfDefectiveResponseDto.builder()
                .defectType(defectType)
                .number(numberOfDefect)
                .date(date)
                .build();
    }

    }
