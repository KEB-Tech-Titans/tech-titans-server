package com.example.techtitansserver.domain.inspection.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.util.Json;
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
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        log.info(jsonNode.toPrettyString());

        return jsonNode;
    }

}
