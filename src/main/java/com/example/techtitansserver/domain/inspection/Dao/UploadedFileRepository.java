package com.example.techtitansserver.domain.inspection.Dao;

import com.example.techtitansserver.domain.inspection.Domain.InspectionResult;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadedFileRepository extends JpaRepository<InspectionResult, Long> {

    Optional<InspectionResult> findByUrl(String fileUrl);

}

