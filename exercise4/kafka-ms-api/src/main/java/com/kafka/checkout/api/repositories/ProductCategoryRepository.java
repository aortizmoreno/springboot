package com.kafka.checkout.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.kafka.checkout.api.models.ProductCategories;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategories, Long> {

}
