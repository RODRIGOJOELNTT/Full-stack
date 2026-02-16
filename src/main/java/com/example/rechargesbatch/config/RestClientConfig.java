package com.example.rechargesbatch.config;

import lombok.RequiredArgsConstructor;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.util.Timeout;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class RestClientConfig {

    private final RechargesApiProperties props;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        RequestConfig requestConfig = RequestConfig.custom()
            // tiempo máximo para obtener una conexión del pool (si aplica)
            .setConnectionRequestTimeout(Timeout.ofMilliseconds(props.getConnectTimeoutMs()))
            // tiempo máximo para establecer conexión TCP
            .setConnectTimeout(Timeout.ofMilliseconds(props.getConnectTimeoutMs()))
            // tiempo máximo de espera de respuesta (equivalente al "read timeout")
            .setResponseTimeout(Timeout.ofMilliseconds(props.getReadTimeoutMs()))
            .build();

        CloseableHttpClient httpClient = HttpClients.custom()
            .setDefaultRequestConfig(requestConfig)
            .build();

        HttpComponentsClientHttpRequestFactory requestFactory =
            new HttpComponentsClientHttpRequestFactory(httpClient);

        return builder
            .requestFactory(() -> requestFactory)
            .build();
    }
}
