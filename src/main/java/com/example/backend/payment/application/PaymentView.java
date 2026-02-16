package com.example.backend.payment.application;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.Instant;

@Value
@Builder
public class PaymentView {
  String id;
  String customerId;
  BigDecimal amount;
  int status;
  Instant createdAt;
}
