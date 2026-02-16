package com.example.backend.payment.api;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.Instant;

@Value
@Builder
public class PaymentResponse {
  String id;
  String customerId;
  BigDecimal amount;
  int status;
  Instant createdAt;
}
