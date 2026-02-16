package com.example.backend.payment.infrastructure.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpringDataCustomerMongoRepository extends MongoRepository<CustomerDocument, String> {
}
