package com.example.techtitansserver.domain.inspection.Service;

import com.example.techtitansserver.domain.inspection.Dao.UploadedFileRepository;
import com.example.techtitansserver.domain.inspection.Domain.InspectionResult;
import com.example.techtitansserver.domain.inspection.Dto.InspectionResultResponseDto;
import com.example.techtitansserver.domain.inspection.Handler.FileHandler;
import java.io.File;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
@Slf4j
public class InspectionService {

    @Value("${resource.file.url}")
    private String fileURL;
    private final UploadedFileRepository uploadedFileRepository;
    private final FileHandler fileHandler;
    private final AiService aiService;

    public InspectionResultResponseDto checkSurfaceWithImage(MultipartFile multipartFile) throws IOException {
        String savedFilePath = fileHandler.saveFile(multipartFile);

        FileSystemResource fileSystemResource = new FileSystemResource(savedFilePath);
        // inspectionResult에서 결함 종류/ 범위 추출해서 등급(rating) 계산

        String resultData = aiService.sendImageToAiServer(fileSystemResource);
        String savedFileName = new File(savedFilePath).getName();
        String url = fileURL + "/" + savedFileName;

        InspectionResult inspectionResult = InspectionResult.create(
                multipartFile.getOriginalFilename(), savedFileName, savedFilePath,
                url, multipartFile.getSize(), multipartFile.getContentType(), "A");
        //"A" 대신 실제 등급 넣기

        save(inspectionResult);

        return InspectionResultResponseDto.toDto(inspectionResult);
    }

    public void save(InspectionResult inspectionResult) {
        uploadedFileRepository.save(inspectionResult);
    }

}
