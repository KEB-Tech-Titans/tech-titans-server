package com.example.techtitansserver.domain.file.Dao;

import com.example.techtitansserver.domain.file.Domain.AnalyzedFile;
import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AnalyzedFileRepository extends JpaRepository<AnalyzedFile, String> {

    @Query("SELECT COUNT(f) FROM AnalyzedFile f")
    Long countFilesStartingWith();

    @Query("SELECT COUNT(f) FROM AnalyzedFile f WHERE YEAR(f.createdAt) = :year")
    Long countFilesStartingWithAndYear(@Param("year") int year);

    @Query("SELECT COUNT(f) FROM AnalyzedFile f WHERE YEAR(f.createdAt) = :year AND MONTH(f.createdAt) = :month")
    Long countFilesStartingWithAndMonth(@Param("year") int year, @Param("month") int month);

    @Query("SELECT COUNT(f) FROM AnalyzedFile f WHERE YEAR(f.createdAt) = :year AND MONTH(f.createdAt) = :month AND DAY(f.createdAt) = :date")
    Long countFilesStartingWithAndDate(@Param("year") int year, @Param("month") int month, @Param("date") int date);

    @Query("SELECT COUNT(f) FROM AnalyzedFile f WHERE f.isPassed = :isPassed")
    Long countFilesByIsPassed(@Param("isPassed") Boolean isPassed);

    @Query("SELECT COUNT(f) FROM AnalyzedFile f WHERE f.isPassed = :isPassed AND YEAR(f.createdAt) = :year")
    Long countFilesByIsPassedAndYear(@Param("isPassed") Boolean isPassed, @Param("year") int year);

    @Query("SELECT COUNT(f) FROM AnalyzedFile f WHERE f.isPassed = :isPassed AND YEAR(f.createdAt) = :year AND MONTH(f.createdAt) = :month")
    Long countFilesByIsPassedAndMonth(@Param("isPassed") Boolean isPassed, @Param("year") int year, @Param("month") int month);

    @Query("SELECT COUNT(f) FROM AnalyzedFile f WHERE f.isPassed = :isPassed AND YEAR(f.createdAt) = :year AND MONTH(f.createdAt) = :month AND DAY(f.createdAt) = :date")
    Long countFilesByIsPassedAndDate(@Param("isPassed") Boolean isPassed, @Param("year") int year, @Param("month") int month, @Param("date") int date);

    @Query("SELECT f.savedFileName FROM AnalyzedFile f WHERE f.createdAt >= :startDate AND f.createdAt <= :endDate")
    Page<AnalyzedFile> getFilesByDuration(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, Pageable pageable);

}
