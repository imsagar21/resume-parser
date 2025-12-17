package com.lck.resume_autofill.service;

import com.lck.resume_autofill.dto.AffindaResponseDTO;
import com.lck.resume_autofill.exeption.ResumeParseException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AffindaClient {

    @Value("${affinda.api.key}")
    private String apiKey;

    @Value("${affinda.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public AffindaResponseDTO parseResume(MultipartFile file) throws IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiKey);
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        });

        HttpEntity<MultiValueMap<String, Object>> request =
                new HttpEntity<>(body, headers);

        ResponseEntity<String> response =
                restTemplate.postForEntity(apiUrl, request, String.class);

        if (response.getBody() == null) {
            throw new ResumeParseException("Affinda parsing failed");
        }

        JsonNode root = objectMapper.readTree(response.getBody());
        JsonNode dataNode = root.get("data");

        return objectMapper.treeToValue(dataNode, AffindaResponseDTO.class);
    }
}
