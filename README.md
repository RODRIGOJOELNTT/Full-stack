# Spring Cloud Gateway (Java 17)

## Run
```bash
mvn spring-boot:run
```

Gateway runs on `http://localhost:8080`.

## Configure routes to your microservices
Edit `src/main/resources/application.yml`:

```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: customers-service
          uri: http://localhost:8081
          predicates:
            - Path=/customers/**
        - id: payments-service
          uri: http://localhost:8082
          predicates:
            - Path=/payments/**
```

### How it works
- `http://localhost:8080/customers/**` -> `http://localhost:8081/customers/**`
- `http://localhost:8080/payments/**`  -> `http://localhost:8082/payments/**`

## Correlation ID propagation
The gateway adds/propagates `X-Correlation-Id` for every request via a `GlobalFilter`.
If the client does not send one, the gateway generates a UUID.

## Actuator
- `GET /actuator/health`
- `GET /actuator/metrics`
- `GET /actuator/prometheus`
- `GET /actuator/gateway/routes`
