package com.fusis.original.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.util.Map;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SummarizationService {

    @Value("${openai.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public String summarize(String text) {
        String url = "https://api.openai.com/v1/chat/completions";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        Map<String, Object> message = Map.of(
                "role", "user",
                "content", "Aşağıdaki randevu talebini 1-2 cümleyle özetle:\n\n" + text
        );

        Map<String, Object> body = Map.of(
                "model", "gpt-3.5-turbo",
                "messages", List.of(message),
                "max_tokens", 150
        );

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);
            List<Map> choices = (List<Map>) response.getBody().get("choices");
            Map firstChoice = choices.get(0);
            Map messageResponse = (Map) firstChoice.get("message");
            return (String) messageResponse.get("content");
        } catch (Exception e) {
            return "Özet alınamadı: " + e.getMessage();
        }
    }
}