package com.example.backend.payment.api;

import com.example.backend.payment.application.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

  private final CreatePaymentUseCase createRecharge;
  private final ListAllPaymentsUseCase listAll;
  private final GetPaymentByIdUseCase getById;
  private final BulkClosePaymentsUseCase bulkClose;

  public PaymentController(CreatePaymentUseCase createRecharge,
      ListAllPaymentsUseCase listAll, GetPaymentByIdUseCase getById,
      BulkClosePaymentsUseCase bulkClose) {
    this.createRecharge = createRecharge;
    this.listAll = listAll;
    this.getById = getById;
    this.bulkClose = bulkClose;
  }


  /** Crea una recarga asociada a un customer (status=1 por defecto) */
  @PostMapping
  public ResponseEntity<PaymentResponse> create(@Valid @RequestBody CreatePaymetRequest req) {
    var view = createRecharge.handle(new CreatePaymentCommand(req.getCustomerId(), req.getAmount()));
    return ResponseEntity.status(201).body(PaymentMappers.toResponse(view));
  }

  /** Consulta TODAS las recargas */
  @GetMapping
  public List<PaymentResponse> listAll() {
    return listAll.handle().stream().map(PaymentMappers::toResponse).toList();
  }

  /** Consulta una recarga por id */
  @GetMapping("/{paymentId}")
  public PaymentResponse get(@PathVariable String paymentId) {
    return PaymentMappers.toResponse(getById.handle(paymentId));
  }

  /** PATCH masivo: status 1 -> 0, sin parámetros */
  @PatchMapping("/status")
  public ResponseEntity<ResponsePaymentStatus> closeAllStatusOne() {
    return ResponseEntity.ok(bulkClose.handle());
  }
}
