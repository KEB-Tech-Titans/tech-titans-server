package com.example.techtitansserver.domain.file.Controller;

import com.example.techtitansserver.domain.file.Dto.UploadedFileResponseDto;
import com.example.techtitansserver.domain.file.Service.FileService;
import com.example.techtitansserver.global.response.ApiResponse;
import com.example.techtitansserver.global.response.Status;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
@Tag(name = "file", description = "파일 api")
public class FileController {

    private final FileService fileService;

    @Operation(summary = "파일 업로드")
    @Secured({"ROLE_USER"})
    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<?> uploadFile(
            @RequestParam(name = "multipartFile") MultipartFile multipartFile
    ) throws IOException {
        UploadedFileResponseDto uploadedFileResponseDto = fileService.saveFile(multipartFile);
        return ApiResponse.onSuccess(Status.CREATED.getCode(), Status.CREATED.getMessage(), uploadedFileResponseDto);
    }

}