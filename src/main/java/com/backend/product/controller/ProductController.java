package com.backend.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.product.models.Product;
import com.backend.product.service.ProductService;

@RestController
public class ProductController {
 
	@Autowired
	private ProductService productService;

	@PostMapping("/product/insert")
	public ResponseEntity<?> insert(@RequestBody Product product) {
		return new ResponseEntity<Product>(productService.insert(product), HttpStatus.CREATED);
	}

	@GetMapping("/product/get/{id}")
	public ResponseEntity<?> getProduct(@PathVariable(required = false) Integer id) {

		Product product = productService.getProduct(id);
		if (product == null) {
			return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@DeleteMapping("/product/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		return new ResponseEntity<String>(productService.delete(id), HttpStatus.OK);
	}

	@PutMapping("/product/update")
	public ResponseEntity<?> update(@RequestBody Product product) {
		Product updatedProduct = productService.update(product);

		if (updatedProduct != null) {
			return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("no record found", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(path = "/product/all", produces = { "application/json" })
	public ResponseEntity<?> allProduct() {
		return new ResponseEntity<List<Product>>(productService.getAllProduct(), HttpStatus.OK);
	}

	@PostMapping("/product/search")
	public ResponseEntity<List<Product>> searchProducts(
			@RequestParam(required = false) String name,
			@RequestParam(required = false) int unitsInStock) {
		List<Product> foundProducts = productService.searchProducts(name, unitsInStock);
		if (foundProducts.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(foundProducts);
	}
}
