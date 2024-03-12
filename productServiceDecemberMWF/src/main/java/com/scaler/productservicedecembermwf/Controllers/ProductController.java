package com.scaler.productservicedecembermwf.Controllers;

import com.scaler.productservicedecembermwf.Exception.ProductNotExistException;
import com.scaler.productservicedecembermwf.Models.Category;
import com.scaler.productservicedecembermwf.Models.Product;
import com.scaler.productservicedecembermwf.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final RestTemplate restTemplate;

    @Autowired
    public ProductController(@Qualifier("selfProductService") ProductService productService, RestTemplate restTemplate) {
        this.productService = productService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/{id}")
    public Product getSingleProduct(@PathVariable("id") Long id) throws ProductNotExistException {
        return productService.getSingleProduct(id);
    }

    @GetMapping() //localhost:8080/products
    public Optional<List<Product>> getAllProducts() {
//        ResponseEntity<List<Product>> response = new ResponseEntity<>(
//                productService.getAllProducts(), HttpStatus.OK
//        );
        return productService.getAllProducts();
    }

    @PostMapping
    public ResponseEntity<Product> addNewProduct(@RequestBody Product product) {
        ResponseEntity<Product> response = new ResponseEntity<>(
                productService.addANewProduct(product),HttpStatus.OK
        );
        return response;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateAProduct(@PathVariable("id") Long id,@RequestBody Product product) {
        ResponseEntity<Product> response = new ResponseEntity<>(
                productService.updateAProduct(id,product), HttpStatus.OK
        );
        return response;
    }

    @GetMapping("/categories")
    public ResponseEntity<Optional<List<Category>>> allCategories() {
        ResponseEntity<Optional<List<Category>>> response = new ResponseEntity<>(
                productService.allCategory(),HttpStatus.OK);

        return response;
    }

    @GetMapping("/category/{ct}")
    public ResponseEntity<List<Product>> specificCategory(@RequestBody String ct) {
        ResponseEntity<List<Product>> response = new ResponseEntity<>(
                productService.specificCategory(ct),HttpStatus.OK);
        return response;
    }
}
