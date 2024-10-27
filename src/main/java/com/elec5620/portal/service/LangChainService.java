package com.elec5620.portal.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@Service
public class LangChainService {

    private static final String API_URL = "http://localhost:5001/api/query";

    public String getAIResponse(String session, String model, String question, String prompt, boolean initialized) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        String requestBody;
        if (!initialized) {
            requestBody = "{"
                    + "\"session_id\":\"" + session + "\","
                    + "\"model_type\":\"" + model + "\","
                    + "\"question\":\"" + question + "\","
                    + "\"prompt\":\"" + prompt + "\""
                    + "}";
        } else {
            requestBody = "{"
                    + "\"session_id\":\"" + session + "\","
                    + "\"model_type\":\"" + model + "\","
                    + "\"question\":\"" + question + "\""
                    + "}";
        }

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(API_URL, HttpMethod.POST, entity, String.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            System.err.println("HTTP Client Error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
            throw e;
        } catch (RestClientException e) {
            System.err.println("Network error occurred: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            throw e;
        }
    }
}
