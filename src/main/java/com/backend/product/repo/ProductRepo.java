package com.backend.product.repo;

import com.backend.product.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Integer> {

    List<Product> findByNameContaining(String name);

    List<Product> findByUnitsInStockGreaterThan(int minUnits);

    List<Product> findByNameContainingAndUnitsInStockGreaterThan(String name, int minUnits);
}
