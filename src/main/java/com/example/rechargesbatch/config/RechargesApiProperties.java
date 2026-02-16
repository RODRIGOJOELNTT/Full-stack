package com.example.rechargesbatch.config;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@ConfigurationProperties(prefix = "api.recharges")
public class RechargesApiProperties {

    @NotBlank
    private String baseUrl;

    @NotBlank
    private String path;

    @Min(1)
    private int connectTimeoutMs = 2000;

    @Min(1)
    private int readTimeoutMs = 5000;

    public String endpoint() {
        return baseUrl + path;
    }
}
