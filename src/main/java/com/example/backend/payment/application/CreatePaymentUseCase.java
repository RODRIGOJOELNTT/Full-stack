package com.example.backend.payment.application;


import com.example.backend.payment.domain.Payment;
import com.example.backend.payment.domain.ports.CustomerRepository;
import com.example.backend.payment.domain.ports.PaymentRepository;
import com.example.backend.shared.domain.NotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;

@Service
public class CreatePaymentUseCase {

  private final PaymentRepository repo;
  private final CustomerRepository customers;

  public CreatePaymentUseCase(PaymentRepository repo, CustomerRepository customers) {
    this.repo = repo;
    this.customers = customers;
  }


  public PaymentView handle(CreatePaymentCommand cmd) {
    // Validación de relación: customer debe existir
    customers.findById(cmd.getCustomerId())
        .orElseThrow(() -> new NotFoundException("Customer not found", Map.of("resource", "Customer", "id", cmd.getCustomerId())));

    Instant now = Instant.now();
    Payment payment = Payment.newPayment(cmd.getCustomerId(), cmd.getAmount(), now);
    return toView(repo.save(payment));
  }

  private static PaymentView toView(Payment r) {
    return PaymentView.builder()
        .id(r.getId())
        .customerId(r.getCustomerId())
        .amount(r.getAmount())
        .status(r.getStatus())
        .createdAt(r.getCreatedAt())
        .build();
  }
}
