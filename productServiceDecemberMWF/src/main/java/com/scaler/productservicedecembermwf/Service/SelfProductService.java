package com.scaler.productservicedecembermwf.Service;

import com.scaler.productservicedecembermwf.Exception.ProductNotExistException;
import com.scaler.productservicedecembermwf.Models.Category;
import com.scaler.productservicedecembermwf.Models.Product;
import com.scaler.productservicedecembermwf.Repository.CategoryRepository;
import com.scaler.productservicedecembermwf.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("selfProductService")
@Primary
public class SelfProductService implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getSingleProduct(Long id) throws ProductNotExistException {

        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()) {
            throw new ProductNotExistException("Product with id: " + id + " doesn't exist.");
        }
        Product product = productOptional.get();
        return product;
    }

    @Override
    public Optional<List<Product>> getAllProducts() {

        Optional<List<Product>> productsList = Optional.of(productRepository.findAll());
        return productsList;
    }

    @Override
    public Product addANewProduct(Product product) {
//        Category category = product.getCategory();
//        if(category.getId() == null) {
//            Category savedCategory = categoryRepository.save(category);
//            product.setCategory(savedCategory);
//        }

        Optional<Category> categoryOptional =
                categoryRepository.findByName(product.getCategory().getName());
        if(categoryOptional.isEmpty()) {
            Category saveCat = categoryRepository.save(product.getCategory());
        } else {
            product.setCategory(categoryOptional.get());
        }
        return productRepository.save(product);
    }

    @Override
    public Product updateAProduct(Long id, Product product) {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()) throw new RuntimeException();

        Product savedProduct = productOptional.get();

        if(product.getTitle() != null) {
            savedProduct.setTitle(product.getTitle());
        }

        if(product.getDescription() != null) {
            savedProduct.setDescription(product.getDescription());
        }

        if(product.getPrice() != null) {
            savedProduct.setPrice(product.getPrice());
        }

        if(product.getImageUrl() != null) {
            savedProduct.setImageUrl(product.getImageUrl());
        }
        return productRepository.save(savedProduct);
    }

    @Override
    public List<Product> specificCategory(String ct) {
        Optional<List<Product>> prodsss = Optional.of(productRepository.findAll());
        if(prodsss.isEmpty()) throw new RuntimeException("Category doesn't exist.");

        List<Product> ans = new ArrayList<>();
        for(Product p: prodsss.get()) {
            if(p.getCategory().getName() == ct) {
                ans.add(p);
            }
        }

        return ans;
    }

    @Override
    public Optional<List<Category>> allCategory() {
        Optional<List<Category>> categoryList = Optional.of(categoryRepository.findAll());
        return categoryList;
    }

    @Override
    public boolean deleteProduct(Long id) {
        return false;
    }
}
