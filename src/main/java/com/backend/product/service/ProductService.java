package com.backend.product.service;

import java.util.List;

import com.backend.product.models.Product;

public interface ProductService {
	public Product insert(Product product);

	public Product getProduct(Integer id);

	public List<Product> getAllProduct();

	public String delete(Integer id);

	public Product update(Product product);

	public List<Product> searchProducts(String name, Integer unitsInStock);
}
