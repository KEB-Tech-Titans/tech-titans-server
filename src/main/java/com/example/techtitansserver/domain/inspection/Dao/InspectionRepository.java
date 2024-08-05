package com.example.techtitansserver.domain.inspection.Dao;

import com.example.techtitansserver.domain.inspection.Domain.DefectType;
import com.example.techtitansserver.domain.inspection.Domain.Inspection;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InspectionRepository extends JpaRepository<Inspection, Long> {

    @Query("SELECT COUNT(d) FROM Inspection d WHERE d.defectType = :defectType")
    Long countDefectByType(@Param("defectType") DefectType defectType);

    @Query("SELECT COUNT(d) FROM Inspection d WHERE d.defectType = :defectType AND d.analyzedFileName = :analyzedFileName")
    Long countDefectByTypeAndFileName(@Param("defectType") DefectType defectType, @Param("analyzedFileName") String analyzedFileName);

    @Query("SELECT COUNT(d) FROM Inspection d WHERE d.defectType = :defectType AND YEAR(d.createdAt) = :year")
    Long countDefectByTypeAndYear(@Param("defectType") DefectType defectType, @Param("year") int year);

    @Query("SELECT COUNT(d) FROM Inspection d WHERE d.defectType = :defectType AND YEAR(d.createdAt) = :year AND MONTH(d.createdAt) = :month")
    Long countDefectByTypeAndMonth(@Param("defectType") DefectType defectType, @Param("year") int year, @Param("month") int month);

    @Query("SELECT COUNT(d) FROM Inspection d WHERE d.defectType = :defectType AND YEAR(d.createdAt) = :year AND MONTH(d.createdAt) = :month AND DAY(d.createdAt) = :date")
    Long countDefectByTypeAndDate(@Param("defectType") DefectType defectType, @Param("year") int year, @Param("month") int month, @Param("date") int date);

    @Query("SELECT COUNT(d) FROM Inspection d WHERE d.defectType = :defectType AND d.analyzedFileName = :analyzedFileName")
    Long getInspectionsByDefectTypeAndAnalyzedFileName(@Param("defectType") DefectType defectType, @Param("analyzedFileName") String analyzedFileName);

    @Query("SELECT d FROM Inspection d WHERE d.id IN (SELECT MIN(subD.id) FROM Inspection subD WHERE subD.defectType = :defectType AND DATE(subD.createdAt) >= :startDate AND DATE(subD.createdAt) <= :endDate GROUP BY subD.analyzedFileName)")
    Page<Inspection> getInspectionsByDefectTypeAndDuration(@Param("defectType") DefectType defectType, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, Pageable pageable);
}
