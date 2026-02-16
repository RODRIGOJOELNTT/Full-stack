package com.example.backend.payment.domain.ports;


import com.example.backend.payment.domain.Customer;
import java.util.Optional;

public interface CustomerRepository {
  Optional<Customer> findById(String id);
}
