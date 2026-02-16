package com.example.backend.payment.application;

import com.example.backend.payment.domain.ports.PaymentRepository;
import com.example.backend.shared.domain.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GetPaymentUseCase {

  private final PaymentRepository repo;

  public GetPaymentUseCase(PaymentRepository repo) {
    this.repo = repo;
  }

  public PaymentView handle(String customerId, String rechargeId) {
    var r = repo.findById(rechargeId)
        .orElseThrow(() -> new NotFoundException("Recharge not found", Map.of("resource", "Recharge", "id", rechargeId)));

    if (!r.getCustomerId().equals(customerId)) {
      // Evita exponer recursos de otro customer
      throw new NotFoundException("Recharge not found", Map.of("resource", "Recharge", "id", rechargeId));
    }

    return PaymentView.builder()
        .id(r.getId())
        .customerId(r.getCustomerId())
        .amount(r.getAmount())
        .status(r.getStatus())
        .createdAt(r.getCreatedAt())
        .build();
  }
}
