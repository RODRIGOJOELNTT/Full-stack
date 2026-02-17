package com.example.apigateway.filters;

import java.util.UUID;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;

@Configuration
public class CorrelationIdFilterConfig {

  public static final String CORRELATION_ID_HEADER = "X-Correlation-Id";

  /**
   * Ensures every request has a correlation id and propagates it downstream to microservices.
   * If the client does not send one, the gateway generates it.
   */
  @Bean
  public GlobalFilter correlationIdFilter() {
    return (exchange, chain) -> {
      String correlationId = exchange.getRequest().getHeaders().getFirst(CORRELATION_ID_HEADER);
      if (correlationId == null || correlationId.isBlank()) {
        correlationId = UUID.randomUUID().toString();
      }

      ServerHttpRequest mutated = exchange.getRequest()
          .mutate()
          .header(CORRELATION_ID_HEADER, correlationId)
          .build();

      return chain.filter(exchange.mutate().request(mutated).build());
    };
  }
}
