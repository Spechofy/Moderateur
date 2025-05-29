package com.example.Moderateur.services.google_api;


import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Comment analyzer.
 */
@Service
public class CommentAnalyzer {


   // @Value("${google.perspective.api.key}")
    private final String apiKey = "AIzaSyBhtQ9wTIOLFf4yowpsdWcinbhShPSVN4k";

    /**
     * The constant MIN_TOXICITY.
     */
    public static final double MIN_TOXICITY = 0.5;

    private static final String API_URL = "https://commentanalyzer.googleapis.com/v1alpha1/comments:analyze?key=";

    /**
     * Analyze toxicity double.
     *
     * @param text the text
     * @return the double
     */
    public double analyzeToxicity(String text) {
        String url = API_URL + apiKey;
        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> request = new HashMap<>();
        request.put("comment", Map.of("text", text));
        request.put("languages", List.of("fr")); // ou "fr" si dispo
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

