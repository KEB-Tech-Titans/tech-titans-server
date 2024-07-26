package com.example.techtitansserver.domain.file.Service;

import com.example.techtitansserver.domain.file.Dao.AnalyzedFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
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

}
