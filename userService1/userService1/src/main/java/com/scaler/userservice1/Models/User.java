package com.scaler.userservice1.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class User extends BaseModel {
    private String email;
    private String username;
    private String password;
    @ManyToOne
    private Name name;
    @ManyToOne
    private Address address;
    private String phone;

}
