package com.example.backend.payment.domain.ports;

import com.example.backend.payment.domain.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository {
  Payment save(Payment recharge);
  Optional<Payment> findById(String id);
  List<Payment> findByCustomerId(String customerId);
  List<Payment> findAll();
  void updateAllStatusFromOneToZero();
}
