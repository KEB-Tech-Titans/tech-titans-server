package com.example.techtitansserver.domain.inspection.Controller;



import com.example.techtitansserver.domain.inspection.Dto.NumberOfDefectiveResponseDto;
import com.example.techtitansserver.domain.inspection.Service.InspectionService;
import com.example.techtitansserver.global.response.ApiResponse;
import com.example.techtitansserver.global.response.Status;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inspection")
@Tag(name = "inspection", description = "검사결과 관련 api")
public class InspectionController {

    private final InspectionService inspectionService;

    @GetMapping("/count/defect")
    @Operation(summary = "결함 종류별 불량 개수 조회하기")
    public ApiResponse<?> getNumberOfDefectByType(
            @RequestParam (required = false) Integer year,
            @RequestParam (required = false) Integer month,
            @RequestParam (required = false) Integer date
    ) {
        List<NumberOfDefectiveResponseDto> numberOfDefectiveResponseDtoList = inspectionService.getNumberOfDefectByType(year, month, date);
        return ApiResponse.onSuccess(Status.CREATED.getCode(), Status.CREATED.getMessage(), numberOfDefectiveResponseDtoList);
    }

    @GetMapping("/count/defect/date")
    @Operation(summary = "날짜별로 결함 종류별 불량 개수 조회하기")
    public ApiResponse<?> getNumberOfDefectByTypeAndDate(
            @RequestParam (required = false) Integer year,
            @RequestParam (required = false) Integer month,
            @RequestParam (required = false) Integer date
    ) {
        List<NumberOfDefectiveResponseDto> numberOfDefectiveResponseDtoList = inspectionService.getNumberOfDefectByTypeAndDate(year, month, date);
        return ApiResponse.onSuccess(Status.CREATED.getCode(), Status.CREATED.getMessage(), numberOfDefectiveResponseDtoList);
    }

    @GetMapping("/count/analyzedFile")
    @Operation(summary = "검사 완료된 파일 개수 조회하기")
    public ApiResponse<?> countInspectionCompleteFile(
            @RequestParam (required = false) Integer year,
            @RequestParam (required = false) Integer month,
            @RequestParam (required = false) Integer date
    ) {
        Long number = inspectionService.countAnalyzedFile(year, month, date);
        return ApiResponse.onSuccess(Status.CREATED.getCode(), Status.CREATED.getMessage(), number);
    }

    @GetMapping("/defectRate")
    @Operation(summary = "불량률 조회하기")
    public ApiResponse<?> getDefectRate(
            @RequestParam (required = false) Integer year,
            @RequestParam (required = false) Integer month,
            @RequestParam (required = false) Integer date
    ) {
        Float defectRate = inspectionService.getDefectRate(year, month, date);
        return ApiResponse.onSuccess(Status.CREATED.getCode(), Status.CREATED.getMessage(), defectRate);
    }
}
