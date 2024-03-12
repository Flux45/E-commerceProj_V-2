package com.scaler.userservice1.Service;


import com.scaler.userservice1.Exceptions.UserNotFoundException;
import com.scaler.userservice1.Models.User;

import java.util.List;
import java.util.Optional;


public interface UserService {
    Optional<List<User>> getAllUsers();
    Optional<User> getSingleUser(Long id) throws UserNotFoundException;
    User updateUser(Long id,User user) throws UserNotFoundException;
    User deleteUser(Long id);
    User addANewUser(User user);

}
