package com.scaler.productservicedecembermwf.Repository;

import com.scaler.productservicedecembermwf.Models.Category;
import com.scaler.productservicedecembermwf.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {


    Optional<Category> findByName(String name);
}
