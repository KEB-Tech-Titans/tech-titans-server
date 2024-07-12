package com.example.techtitansserver.domain.inspection.Controller;

import com.example.techtitansserver.domain.inspection.Dto.InspectionResultResponseDto;
import com.example.techtitansserver.domain.inspection.Service.InspectionService;
import com.example.techtitansserver.global.response.ApiResponse;
import com.example.techtitansserver.global.response.Status;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
@RestController
@RequiredArgsConstructor
@RequestMapping("/inspection")
@Tag(name = "inspection", description = "점검 api")
public class InspectionController {

    private final InspectionService inspectionService;

    @Operation(summary = "이미지를 통한 점검")
    @Secured({"ROLE_USER"})
    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<?> uploadFile(
            @RequestParam(name = "multipartFile") MultipartFile multipartFile
    ) throws IOException {
        InspectionResultResponseDto inspectionResultResponseDto = inspectionService.checkSurfaceWithImage(multipartFile);
        return ApiResponse.onSuccess(Status.CREATED.getCode(), Status.CREATED.getMessage(), inspectionResultResponseDto);
    }

}