package com.scaler.userservice1.Models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Address extends BaseModel {
    private String city;
    private String street;
    private String zipcode;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    private GeoLocation geoLocation;
    private Long number;
}
