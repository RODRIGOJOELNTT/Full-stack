package com.example.backend.payment.infrastructure.mongo;



import com.example.backend.payment.domain.Customer;
import com.example.backend.payment.domain.ports.CustomerRepository;
import com.example.backend.shared.domain.DomainException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerMongoAdapter implements CustomerRepository {

  private final SpringDataCustomerMongoRepository mongo;

  public CustomerMongoAdapter(SpringDataCustomerMongoRepository mongo) {
    this.mongo = mongo;
  }

  @Override
  public Optional<Customer> findById(String id) {
    return mongo.findById(id).map(CustomerMongoAdapter::toDomain);
  }

  private static Customer toDomain(CustomerDocument d) {
    return new Customer(d.getId(), d.getName(), d.getEmail(), d.getCreatedAt(), d.getUpdatedAt());
  }
}
