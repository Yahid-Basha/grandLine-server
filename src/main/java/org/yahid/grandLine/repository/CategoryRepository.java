package org.yahid.grandLine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yahid.grandLine.model.Category;
import org.yahid.grandLine.model.CategoryInfo;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category getCategoryByName(String name);
}