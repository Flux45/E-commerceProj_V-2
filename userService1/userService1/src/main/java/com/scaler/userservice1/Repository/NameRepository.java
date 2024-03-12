package com.scaler.userservice1.Repository;

import com.scaler.userservice1.Models.Name;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NameRepository extends JpaRepository<Name, Long> {
    Optional<Name> findByFirstNameAndLastName(String firstName, String lastName);

    Optional<Name> findByFirstName(String firstName);
}
