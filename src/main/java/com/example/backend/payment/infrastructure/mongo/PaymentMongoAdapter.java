package com.example.backend.payment.infrastructure.mongo;

import com.example.backend.payment.domain.Payment;
import com.example.backend.payment.domain.ports.PaymentRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PaymentMongoAdapter implements PaymentRepository {

  private final SpringDataPaymentMongoRepository mongo;

  public PaymentMongoAdapter(SpringDataPaymentMongoRepository mongo) {
    this.mongo = mongo;
  }

  @Override
  public Payment save(Payment recharge) {
    return toDomain(mongo.save(toDoc(recharge)));
  }

  @Override
  public Optional<Payment> findById(String id) {
    return mongo.findById(id).map(PaymentMongoAdapter::toDomain);
  }

  @Override
  public List<Payment> findByCustomerId(String customerId) {
    return mongo.findByCustomerId(customerId).stream().map(PaymentMongoAdapter::toDomain).toList();
  }

  
  @Override
  public List<Payment> findAll() {
    return mongo.findAll().stream().map(PaymentMongoAdapter::toDomain).toList();
  }

  @Override
  public void updateAllStatusFromOneToZero() {
    mongo.updateStatusFromOneToZero();
  }

private static PaymentDocument toDoc(Payment r) {
    return PaymentDocument.builder()
        .id(r.getId())
        .customerId(r.getCustomerId())
        .amount(r.getAmount())
        .status(r.getStatus())
        .createdAt(r.getCreatedAt())
        .build();
  }

  private static Payment toDomain(PaymentDocument d) {
    return new Payment(d.getId(), d.getCustomerId(), d.getAmount(), d.getStatus(), d.getCreatedAt());
  }
}
