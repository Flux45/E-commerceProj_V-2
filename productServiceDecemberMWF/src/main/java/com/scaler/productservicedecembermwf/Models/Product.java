package com.scaler.productservicedecembermwf.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel {

    private String title;
    private Double price;
    @ManyToOne
    private Category category;
    private String description;
    private String imageUrl;
}

//    1     ->    1
// Product  -> Category
//    M     ->     1
//    m      :     1