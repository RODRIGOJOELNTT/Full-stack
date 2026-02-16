package com.example.backend.payment.application;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class CreatePaymentCommand {
  String customerId;
  BigDecimal amount;
}
