package com.scaler.productservicedecembermwf.Repository;


import com.scaler.productservicedecembermwf.Models.Category;
import com.scaler.productservicedecembermwf.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository
        extends JpaRepository<Product, Long> {

    List<Product> findByTitleContaining(String word);

    List<Product> findByCategory_Name(String name);

    void deleteByTitle(String title);

    List<Product> findByCategory_Id(Long id);

    Optional<Product> findById(Long id);

    Product save(Product product);

}
