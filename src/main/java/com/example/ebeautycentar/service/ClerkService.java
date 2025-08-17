package com.example.ebeautycentar.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class ClerkService {

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    @Value("${clerk.secret.key}")
    private String clerkSecretKey;

    public ClerkService() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Briše korisnika iz Clerk-a po email adresi
     */
    public boolean deleteUserByEmail(String email) {
        try {
            // 1. Pronalazi korisnika po email-u
            HttpRequest getRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.clerk.dev/v1/users?email_address=" + email))
                    .header("Authorization", "Bearer " + clerkSecretKey)
                    .header("Content-Type", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

            if (getResponse.statusCode() != 200) {
                System.err.println("Clerk GET failed: " + getResponse.body());
                return false;
            }

            JsonNode root = objectMapper.readTree(getResponse.body());
            if (!root.isArray() || root.size() == 0) {
                System.err.println("No Clerk user found with email: " + email);
                return false;
            }

            String clerkUserId = root.get(0).get("id").asText();

            // 2. Brise korisnika po userId-u
            HttpRequest deleteRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.clerk.dev/v1/users/" + clerkUserId))
                    .header("Authorization", "Bearer " + clerkSecretKey)
                    .DELETE()
                    .build();

            HttpResponse<String> deleteResponse = httpClient.send(deleteRequest, HttpResponse.BodyHandlers.ofString());

            if (deleteResponse.statusCode() == 200 || deleteResponse.statusCode() == 204) {
                System.out.println("Clerk user deleted: " + clerkUserId);
                return true;
            } else {
                System.err.println("Failed to delete Clerk user: " + deleteResponse.body());
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
