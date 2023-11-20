package com.backend.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.product.repo.ProductRepo;

import com.backend.product.models.Product;
import com.backend.product.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ProductServiceImpl implements ProductService {

	private final Logger logger = LoggerFactory.getLogger(ProductService.class);

	@Autowired
	private ProductRepo productRepo;

	/**
	 * Inserts a new product into the repository.
	 *
	 * @param product The product to be inserted.
	 * @return The inserted product.
	 */
	@Override
	
	public Product insert(Product product) {
		try {
			return productRepo.save(product);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Retrieves a product based on the provided ID.
	 *
	 * @param id The ID of the product to retrieve.
	 * @return The retrieved product, or null if not found.
	 */
	@Override
	
	public Product getProduct(Integer id) {
		try {
			return productRepo.findById(id).get();
		} catch (Exception e) {
			logger.error("Error in GetproductbyID", e);
			return null;
		}
	}

	/**
	 * Retrieves a list of all products.
	 *
	 * @return A list of all products, or null if an error occurs.
	 */
	@Override
	
	public List<Product> getAllProduct() {
		try {
			return productRepo.findAll();
		} catch (Exception e) {
			logger.error("Error in Getting AllProduct", e);
			return null;
		}
	}

	/**
	 * Deletes a product based on the provided ID.
	 *
	 * @param id The ID of the product to delete.
	 * @return A message indicating the result of the deletion.
	 */
	@Override
	
	public String delete(Integer id) {
		try {
			productRepo.deleteById(id);
			return "product deleted successfully";
		} catch (Exception e) {
			logger.error("Error in deleting product", e);
			return "error deleting product";
		}
	}

	/**
	 * Updates an existing product in the repository.
	 *
	 * @param product The updated product.
	 * @return The updated product.
	 */
	@Override
	
	public Product update(Product product) {
		try {
			Integer productId = product.getId();
			if (!productRepo.existsById(productId)) {
				logger.error("Product with ID {} not found for update", productId);
				return null;
			}
			return productRepo.save(product);
		} catch (Exception e) {
			logger.error("Error updating product", e);
			return null;
		}
	}

	/**
	 * Search an existing product and their quantity in the repository.
	 *
	 * @param name
	 * @param unitsInStock
	 * @return The searched product.
	 */
	@Override
	
	public List<Product> searchProducts(String name, Integer unitsInStock) {
		try {
			List<Product> products;

			if (name != null && unitsInStock != null) {
				products = productRepo.findByNameContainingAndUnitsInStockGreaterThan(name, unitsInStock);
			} else if (name != null) {
				products = productRepo.findByNameContaining(name);
			} else if (unitsInStock != null) {
				products = productRepo.findByUnitsInStockGreaterThan(unitsInStock);
			} else {
				products = productRepo.findAll();
			}

			return products;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
