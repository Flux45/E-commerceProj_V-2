package com.scaler.productservicedecembermwf.Service;

import  com.scaler.productservicedecembermwf.Exception.ProductNotExistException;
import com.scaler.productservicedecembermwf.Models.Category;
import com.scaler.productservicedecembermwf.Models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product getSingleProduct(Long id) throws ProductNotExistException;

    Optional<List<Product>> getAllProducts();

    Product addANewProduct(Product product);

    Product updateAProduct(Long id,Product product);

    List<Product> specificCategory(String ct);

    Optional<List<Category>> allCategory();

    boolean deleteProduct(Long id);
}
