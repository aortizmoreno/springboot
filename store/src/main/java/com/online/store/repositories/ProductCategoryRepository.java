package com.online.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.online.store.models.ProductCategories;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategories, Long> {
    // @Query(
        // value = "SELECT group_concat(tcategories.name) categories FROM (SELECT TRIM(name) name FROM store.product_categories) tcategories",
    //     value = "WITH tcategories AS (SELECT TRIM(name) name FROM store.product_categories) SELECT group_concat(tcategories.name) categories FROM tcategories",
    //     nativeQuery = true
    // )
    // String findAllCategories();
}
