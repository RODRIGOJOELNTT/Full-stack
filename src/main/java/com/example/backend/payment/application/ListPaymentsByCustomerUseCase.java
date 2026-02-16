package com.example.backend.payment.application;

import com.example.backend.payment.domain.ports.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListPaymentsByCustomerUseCase {

  private final PaymentRepository repo;

  public ListPaymentsByCustomerUseCase(PaymentRepository repo) {
    this.repo = repo;
  }

  public List<PaymentView> handle(String customerId) {
    return repo.findByCustomerId(customerId).stream()
        .map(r -> PaymentView.builder()
            .id(r.getId())
            .customerId(r.getCustomerId())
            .amount(r.getAmount())
            .status(r.getStatus())
            .createdAt(r.getCreatedAt())
            .build())
        .toList();
  }
}
