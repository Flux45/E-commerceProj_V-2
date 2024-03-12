package com.scaler.userservice1.Repository;

import com.scaler.userservice1.Models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
    Optional<Address> findByZipcode(String zipcode);
}
