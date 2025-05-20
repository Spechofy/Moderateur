package com.example.Moderateur.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PerspectiveService {

    @Value("${google.perspective.api.key}")
    private String apiKey;

    private static final String API_URL = "https://commentanalyzer.googleapis.com/v1alpha1/comments:analyze?key=";

    public double analyzeToxicity(String text) {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> request = new HashMap<>();
        request.put("comment", Map.of("text", text));
        request.put("languages", List.of("en")); // ou "fr" si dispo
        request.put("requestedAttributes", Map.of("TOXICITY", new HashMap<>()));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);

        ResponseEntity<Map> response = restTemplate.exchange(
                API_URL + apiKey,
                HttpMethod.POST,
                entity,
                Map.class
        );

        // Extraire le score de TOXICITY
        Map responseBody = response.getBody();
        if (responseBody != null) {
            Map attributeScores = (Map) responseBody.get("attributeScores");
            Map toxicity = (Map) attributeScores.get("TOXICITY");
            Map summaryScore = (Map) toxicity.get("summaryScore");
            return (Double) summaryScore.get("value");
        }

        return 0.0;
    }
}

