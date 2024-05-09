package br.com.alura.DaysOfCode.ApplicationConfigs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate() {
        System.out.println("RestTemplate bean created and registered");
        return new RestTemplate();
    }
}
