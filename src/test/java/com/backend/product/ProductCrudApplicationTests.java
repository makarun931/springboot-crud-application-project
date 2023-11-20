package com.backend.product;
import com.backend.product.controller.ProductController;
import com.backend.product.models.Product;
import com.backend.product.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;

public class ProductCrudApplicationTests {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        // Initialize the controller and mock the service
    }

    @Test
    public void testInsertProduct() {
        Product product = new Product();
        when(productService.insert(product)).thenReturn(product);

        ResponseEntity<?> response = productController.insert(product);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testGetProduct() {
        int productId = 1;
        Product product = new Product();
        when(productService.getProduct(productId)).thenReturn(product);

        ResponseEntity<?> response = productController.getProduct(productId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetProductNotFound() {
        int productId = 1;
        when(productService.getProduct(productId)).thenReturn(null);

        ResponseEntity<?> response = productController.getProduct(productId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Product not found", response.getBody());
    }

    @Test
    public void testDeleteProduct() {
        int productId = 1;
        when(productService.delete(productId)).thenReturn("Product deleted successfully");

        ResponseEntity<?> response = productController.delete(productId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Product deleted successfully", response.getBody());
    }

    @Test
    public void testUpdateProduct() {
        Product product = new Product();
        when(productService.update(product)).thenReturn(product);

        ResponseEntity<?> response = productController.update(product);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testUpdateProductNotFound() {
        Product product = new Product();
        when(productService.update(product)).thenReturn(null);

        ResponseEntity<?> response = productController.update(product);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("no record found", response.getBody());
    }

    @Test
    public void testGetAllProducts() {
        List<Product> productList = new ArrayList<>();
        when(productService.getAllProduct()).thenReturn(productList);

        ResponseEntity<?> response = productController.allProduct();

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testSearchProducts() {
        String name = "Product";
        int unitsInStock = 10;
        List<Product> productList = new ArrayList<>();
        when(productService.searchProducts(name, unitsInStock)).thenReturn(productList);

        ResponseEntity<List<Product>> response = productController.searchProducts(name, unitsInStock);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testSearchProductsNotFound() {
        String name = "Product";
        int unitsInStock = 10;
        List<Product> productList = new ArrayList<>();
        when(productService.searchProducts(name, unitsInStock)).thenReturn(new ArrayList<>());

        ResponseEntity<List<Product>> response = productController.searchProducts(name, unitsInStock);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
