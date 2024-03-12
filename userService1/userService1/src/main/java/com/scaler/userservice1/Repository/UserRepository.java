package com.scaler.userservice1.Repository;

import com.scaler.userservice1.Models.Name;
import com.scaler.userservice1.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {


    @Override
    Optional<User> findById(Long id);

    @Override
    List<User> findAll();

    List<User> findByName(Name name);

    User save(User user);



}
