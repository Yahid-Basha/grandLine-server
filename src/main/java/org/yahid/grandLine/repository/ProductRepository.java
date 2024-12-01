package org.yahid.apibuild.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yahid.apibuild.model.Product;
import org.yahid.apibuild.model.ProductInfo;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<ProductInfo> findAllByOrderByCreatedAtDesc();

    Optional<ProductInfo> findProductsByName(String name);

}