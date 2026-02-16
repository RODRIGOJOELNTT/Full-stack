package com.example.backend.payment.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Payment {

  private final String id;
  private final String customerId;
  private final BigDecimal amount;
  private final int status; // 1 = ACTIVE (por ahora)
  private final Instant createdAt;

  public static Payment newPayment(String customerId, BigDecimal amount, Instant now) {
    return new Payment(UUID.randomUUID().toString(), customerId, amount, 1, now);
  }
}
