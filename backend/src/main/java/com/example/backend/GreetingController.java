package com.example.backend;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api") // This prefix matches the Nginx proxy configuration
public class GreetingController {

    /**
     * This endpoint provides a simple greeting message.
     * It can be accessed at http://localhost:4200/api/greeting
     * @return A greeting string.
     */
    @GetMapping("/greeting")
    public String getGreeting() {
        return "Hello from the Spring Boot backend!";
    }

    /**
     * A simple health check endpoint.
     * It can be accessed at http://localhost:4200/api/health
     * @return A status message.
     */
    @GetMapping("/health")
    public String healthCheck() {
        return "{\"status\": \"UP\"}";
    }

    /**
     * A catch-all endpoint to handle requests to non-existent paths under /api.
     * This prevents the default "Whitelabel Error Page".
     * @return A ResponseEntity with a 404 status and an error message.
     */
    @GetMapping("/**")
    public ResponseEntity<String> handleUnknownEndpoints() {
        return new ResponseEntity<>("{\"error\": \"Not Found\", \"message\": \"The requested endpoint does not exist.\"}", HttpStatus.NOT_FOUND);
    }
}
