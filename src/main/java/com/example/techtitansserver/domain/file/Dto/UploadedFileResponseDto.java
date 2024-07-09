package com.example.techtitansserver.domain.file.Dto;

import com.example.techtitansserver.domain.file.Domain.UploadedFile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UploadedFileResponseDto {

    private Long id;

    private String originalFileName;

    private String url;

    public static UploadedFileResponseDto toDto(UploadedFile uploadedFile) {
        return UploadedFileResponseDto.builder()
                .id(uploadedFile.getId())
                .originalFileName(uploadedFile.getOriginalFileName())
                .url(uploadedFile.getUrl())
                .build();
    }

}
