package org.yahid.apibuild.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yahid.apibuild.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}