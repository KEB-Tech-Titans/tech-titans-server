package com.example.techtitansserver.domain.file.Dao;

import com.example.techtitansserver.domain.file.Domain.UploadedFile;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadedFileRepository extends JpaRepository<UploadedFile, Long> {

    Optional<UploadedFile> findByUrl(String fileUrl);

}

