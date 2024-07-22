package com.example.techtitansserver.domain.inspection.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import java.util.Base64;

@RequiredArgsConstructor
@Service
@Slf4j
public class AiService {

    private static final String BASE_URL = "http://localhost:5000";
    private final RestClient restClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public JsonNode sendImageToAiServer(FileSystemResource fileSystemResource) throws IOException {

        MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
        parts.add("imageFile", fileSystemResource);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        ResponseEntity<String> response = restClient.post()
                .uri(BASE_URL + "/analyze")
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .body(parts)
                .retrieve()
                .toEntity(String.class);

        String responseBody = response.getBody();
        JsonNode jsonResponse = objectMapper.readTree(responseBody);

        String imageBase64 = jsonResponse.path("image").asText();
        JsonNode inspections = jsonResponse.path("inspections");

        // S3에 저장하고 파일 경로를 responseDto에 담는 방식으로 수정 예정
        File imageFile = base64ToFile(imageBase64, "analyzed_image.png");
        FileSystemResource imageResource = new FileSystemResource(imageFile);

        return inspections;
    }

    public File base64ToFile(String base64, String fileName) throws IOException {
        byte[] decodedBytes = Base64.getDecoder().decode(base64);
        File file = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(decodedBytes);
        }
        return file;
    }

}
