package com.example.backend.payment.infrastructure.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import org.springframework.data.mongodb.repository.Update;

public interface SpringDataPaymentMongoRepository extends MongoRepository<PaymentDocument, String> {
  List<PaymentDocument> findByCustomerId(String customerId);

  @Query("{ 'status' : 1 }")
  @Update("{ '$set' : { 'status' : 0 } }")
  void updateStatusFromOneToZero();
}
