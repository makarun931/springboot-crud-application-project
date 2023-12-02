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

	private Product product;

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
			 product = new Product();
			// Round off the unit price based on the specified criteria
            Double roundedUnitPrice = roundOffUnitPrice(product.getUnitPrice());
            product.setUnitPrice(roundedUnitPrice);
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
			 // Round off the unit price based on the specified criteria
            Double roundedUnitPrice = roundOffUnitPrice(product.getUnitPrice());
            product.setUnitPrice(roundedUnitPrice);
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
	
	 private static Double roundOffUnitPrice(Double unitPrice) {
	        if (unitPrice != null) {
	            if (unitPrice < 1.0) {
	                // If unit price is less than 1 rupee, round up to 1 using Math.ceil
	                return 1.0;
	            } else {
	                // If unit price is greater than or equal to 1 rupee, round off to the nearest whole number
	                double decimalPart = unitPrice - Math.floor(unitPrice);
	                if (decimalPart < 0.5) {
	                    // If the decimal part is less than 0.5, round down using Math.floor
	                    return Math.floor(unitPrice);
	                } else {
	                    // If the decimal part is greater than or equal to 0.5, round up using Math.ceil
	                    return Math.ceil(unitPrice);
	                }
	            }
	        }
	        // If unit price is null, return null
	        return null;
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

	        // Add switch case for filtering based on categories
	        switch (getFilterCategory(name, unitsInStock)) {
	            case NAME_AND_UNITS_IN_STOCK:
	                products = productRepo.findByNameContainingAndUnitsInStockGreaterThan(name, unitsInStock);
	                break;
	            case NAME_ONLY:
	                products = productRepo.findByNameContaining(name);
	                break;
	            case UNITS_IN_STOCK_ONLY:
	                products = productRepo.findByUnitsInStockGreaterThan(unitsInStock);
	                break;
	            default:
	                products = productRepo.findAll();
	                break;
	        }

	        return products;
	    } catch (Exception e) {
	        logger.error("Error searching products", e);
	        return null;
	    }
	}

	// Define an enum for filter categories
	private enum FilterCategory {
	    NAME_AND_UNITS_IN_STOCK,
	    NAME_ONLY,
	    UNITS_IN_STOCK_ONLY,
	    NONE
	}

	// Helper method to determine the filter category
	private FilterCategory getFilterCategory(String name, Integer unitsInStock) {
	    if (name != null && unitsInStock != null) {
	        return FilterCategory.NAME_AND_UNITS_IN_STOCK;
	    } else if (name != null) {
	        return FilterCategory.NAME_ONLY;
	    } else if (unitsInStock != null) {
	        return FilterCategory.UNITS_IN_STOCK_ONLY;
	    } else {
	        return FilterCategory.NONE;
	    }

	}
}
