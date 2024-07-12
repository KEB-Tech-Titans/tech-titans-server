package com.example.techtitansserver.domain.inspection.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
@Slf4j
public class AiService {

    private static final String BASE_URL = "http://localhost:5000";
    private final RestClient restClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String sendImageToAiServer(MultipartFile multipartFile) throws IOException {

        Path tempFile = Files.createTempFile("upload", multipartFile.getOriginalFilename());

        FileSystemResource fileResource = new FileSystemResource(tempFile.toFile());
        MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
        parts.add("imageFile", fileResource);

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
        String data = jsonResponse.path("data").asText();

        return data;
    }

}
