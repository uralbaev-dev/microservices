package org.amigoscode.customer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @className: CustomerConfig
 * @date: 15.08.2023
 * @author: Uralbaev Diyorbek
 */

@Configuration
public class CustomerConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
