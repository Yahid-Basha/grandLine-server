package org.yahid.apibuild.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yahid.apibuild.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}