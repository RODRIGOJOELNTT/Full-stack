package com.example.backend.payment.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class ResponsePaymentStatus {
  Boolean status;
}
