package org.yahid.grandLine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yahid.grandLine.model.Customer;
import org.yahid.grandLine.model.CustomerInfo;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> getCustomersByEmail(String email);
}