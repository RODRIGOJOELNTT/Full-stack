package com.example.backend.payment.api;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
@NoArgsConstructor
public class UpdatePaymentRequest {

  @NotNull
  @DecimalMin(value = "0.01", inclusive = true, message = "must be >= 0.01")
  private BigDecimal amount;
}
