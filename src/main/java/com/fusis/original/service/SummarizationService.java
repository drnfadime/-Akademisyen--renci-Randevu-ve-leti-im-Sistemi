package com.fusis.original.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.util.Map;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SummarizationService {

    @Value("${openai.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public String summarize(String text) {
        String url = "https://api.openai.com/v1/chat/completions";

        log.info("Özetleme isteği başlatıldı. Metin uzunluğu: {} karakter", text.length());

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
            log.info("API yanıtı alındı. Status: {}", response.getStatusCode());

            List<Map> choices = (List<Map>) response.getBody().get("choices");
            Map firstChoice = choices.get(0);
            Map messageResponse = (Map) firstChoice.get("message");
            String summary = (String) messageResponse.get("content");

            log.info("Özet başarıyla oluşturuldu.");
            return summary;

        } catch (Exception e) {
            log.error("API hatası oluştu: {}", e.getMessage());
            return "Özet alınamadı: " + e.getMessage();
        }
    }
}