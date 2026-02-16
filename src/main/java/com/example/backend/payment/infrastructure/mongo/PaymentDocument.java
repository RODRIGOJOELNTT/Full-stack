package com.example.backend.payment.infrastructure.mongo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;

@Document(collection = "payments")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDocument {
  @Id
  private String id;

  @Indexed
  private String customerId;

  private BigDecimal amount;

  private int status;

  private Instant createdAt;
}
