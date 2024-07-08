package com.example.techtitansserver.domain.file.Service;

import com.example.techtitansserver.domain.file.Dao.UploadedFileRepository;
import com.example.techtitansserver.domain.file.Domain.UploadedFile;
import com.example.techtitansserver.domain.file.Dto.UploadedFileResponseDto;
import com.example.techtitansserver.domain.file.Handler.FileHandler;
import java.io.File;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
@Slf4j
public class FileService {

    @Value("${resource.file.url}")
    private String fileURL;

    private final UploadedFileRepository uploadedFileRepository;

    private final FileHandler fileHandler;

    public UploadedFileResponseDto saveFile(MultipartFile multipartFile) throws IOException {
        String savedFilePath = fileHandler.saveFile(multipartFile);
        String savedFileName = new File(savedFilePath).getName();
        String url = fileURL + "/" + savedFileName;

        UploadedFile uploadedFile = UploadedFile.create(
                multipartFile.getOriginalFilename(), savedFileName, savedFilePath,
                url, multipartFile.getSize(), multipartFile.getContentType());
        save(uploadedFile);
        return UploadedFileResponseDto.toDto(uploadedFile);
    }

    public void save(UploadedFile uploadedFile) {
        uploadedFileRepository.save(uploadedFile);
    }


}
