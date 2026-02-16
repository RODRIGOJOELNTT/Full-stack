package com.example.backend.payment.api;

import com.example.backend.payment.application.PaymentView;

public class PaymentMappers {
  private PaymentMappers() {}

  public static PaymentResponse toResponse(PaymentView v) {
    return PaymentResponse.builder()
        .id(v.getId())
        .customerId(v.getCustomerId())
        .amount(v.getAmount())
        .status(v.getStatus())
        .createdAt(v.getCreatedAt())
        .build();
  }
}
