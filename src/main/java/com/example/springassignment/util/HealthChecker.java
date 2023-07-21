package com.example.springassignment.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

@EnableScheduling
@Configuration
public class HealthChecker {
    private RestTemplate restTemplate = new RestTemplate();
    private static final String healthUrl = "http://localhost:8080/actuator/health/custom";

    @Scheduled(fixedRate = 10*60*1000)
    public void checkHealth(){
        String response = restTemplate.getForObject(healthUrl,String.class);
        System.out.println(response);
    }

}
