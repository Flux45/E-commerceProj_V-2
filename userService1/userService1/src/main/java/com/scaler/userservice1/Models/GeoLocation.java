package com.scaler.userservice1.Models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class GeoLocation extends BaseModel {
    private Double lat;
    private Double longitude;
}
