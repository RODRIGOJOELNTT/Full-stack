package com.example.backend.payment.application;

import com.example.backend.payment.api.ResponsePaymentStatus;
import com.example.backend.payment.domain.Payment;
import com.example.backend.payment.domain.ports.PaymentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BulkClosePaymentsUseCase {

  private final PaymentRepository repo;

  public BulkClosePaymentsUseCase(PaymentRepository repo) {
    this.repo = repo;
  }

  /**
   * Actualiza TODAS las recargas con status=1 a status=0.
   */
  public ResponsePaymentStatus handle() {
    repo.updateAllStatusFromOneToZero();
    return toStatus(new ResponsePaymentStatus(Boolean.TRUE));
  }

  private static ResponsePaymentStatus toStatus(ResponsePaymentStatus s) {
    return ResponsePaymentStatus.builder()
        .status(s.getStatus())
        .build();
  }
}
