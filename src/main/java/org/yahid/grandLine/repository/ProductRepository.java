package org.yahid.grandLine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yahid.grandLine.model.Product;
import org.yahid.grandLine.model.ProductInfo;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<ProductInfo> findAllByOrderByCreatedAtDesc();

    Optional<ProductInfo> findProductsByName(String name);

}