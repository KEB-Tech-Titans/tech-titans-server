package com.example.techtitansserver.domain.file.Service;

import com.example.techtitansserver.domain.file.Dao.AnalyzedFileRepository;
import com.example.techtitansserver.domain.file.Domain.AnalyzedFile;
import com.example.techtitansserver.global.response.GeneralException;
import com.example.techtitansserver.global.response.Status;
import java.time.LocalDate;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.GeoPage;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileService {

    private final AnalyzedFileRepository analyzedFileRepository;

    public Long countAnalyzedFile(Integer year, Integer month, Integer date) {
        Long numberOfFiles;
        if (year == null)
            numberOfFiles = analyzedFileRepository.countFilesStartingWith();
        else if (month == null)
            numberOfFiles = analyzedFileRepository.countFilesStartingWithAndYear(year);
        else if (date == null)
            numberOfFiles = analyzedFileRepository.countFilesStartingWithAndMonth(year, month);
        else
            numberOfFiles = analyzedFileRepository.countFilesStartingWithAndDate(year, month, date);
        return numberOfFiles;
    }

    public Long countFilesByIsPassed(Integer year, Integer month, Integer date, Boolean isPassed) {
        Long numberOfPassedFile;
        if (year == null)
            numberOfPassedFile = analyzedFileRepository.countFilesByIsPassed(isPassed);
        else if (month == null)
            numberOfPassedFile = analyzedFileRepository.countFilesByIsPassedAndYear(isPassed, year);
        else if (date == null)
            numberOfPassedFile = analyzedFileRepository.countFilesByIsPassedAndMonth(isPassed, year, month);
        else
            numberOfPassedFile = analyzedFileRepository.countFilesByIsPassedAndDate(isPassed, year, month, date);
        return numberOfPassedFile;
    }

    public Page<AnalyzedFile> getAnalyzedFileByDuration(LocalDate startDate, LocalDate endDate, Pageable pageable) {
        return analyzedFileRepository.getFilesByDuration(startDate, endDate, pageable);
    }

    public AnalyzedFile getAnalyzedFileById(String savedFileName) {
        log.info(savedFileName);
        return analyzedFileRepository.findById(savedFileName)
                .orElseThrow(()-> new GeneralException(Status.BAD_REQUEST));
    }

}
