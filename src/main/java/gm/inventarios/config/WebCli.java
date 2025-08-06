package gm.inventarios.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebCli {

    @Bean
    public WebClient webClient() {
        return WebClient.builder().baseUrl("https://openlibrary.org")
                .build();
    }
}

