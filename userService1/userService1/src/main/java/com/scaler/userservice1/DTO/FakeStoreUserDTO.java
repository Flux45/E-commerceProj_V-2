package com.scaler.userservice1.DTO;

import com.scaler.userservice1.Models.Address;
import com.scaler.userservice1.Models.Name;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreUserDTO {
    private Address address;
    private Long id;
    private String email;
    private String username;
    private String password;
    private Name name;
    private String phone;

}
