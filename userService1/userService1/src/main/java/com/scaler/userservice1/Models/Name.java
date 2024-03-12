package com.scaler.userservice1.Models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Name extends BaseModel {
    private String firstName;
    private String lastName;
}
