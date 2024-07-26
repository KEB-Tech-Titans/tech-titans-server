package com.example.techtitansserver.domain.inspection.Dao;

import com.example.techtitansserver.domain.inspection.Domain.DefectType;
import com.example.techtitansserver.domain.inspection.Domain.Inspection;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InspectionRepository extends JpaRepository<Inspection, Long> {

    @Query("SELECT COUNT(d) FROM Inspection d WHERE d.defectType = :defectType")
    Long countDefectByType(@Param("defectType") DefectType defectType);

    @Query("SELECT COUNT(d) FROM Inspection d WHERE d.defectType = :defectType AND YEAR(d.createdAt) = :year")
    Long countDefectByTypeAndYear(@Param("defectType") DefectType defectType, @Param("year") int year);

    @Query("SELECT COUNT(d) FROM Inspection d WHERE d.defectType = :defectType AND YEAR(d.createdAt) = :year AND MONTH(d.createdAt) = :month")
    Long countDefectByTypeAndMonth(@Param("defectType") DefectType defectType, @Param("year") int year, @Param("month") int month);

    @Query("SELECT COUNT(d) FROM Inspection d WHERE d.defectType = :defectType AND YEAR(d.createdAt) = :year AND MONTH(d.createdAt) = :month AND DAY(d.createdAt) = :date")
    Long countDefectByTypeAndDate(@Param("defectType") DefectType defectType, @Param("year") int year, @Param("month") int month, @Param("date") int date);

}
