package com.scaler.userservice1.Controller;

import com.scaler.userservice1.Exceptions.UserNotFoundException;
import com.scaler.userservice1.Models.User;
import com.scaler.userservice1.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private RestTemplate restTemplate;

    @Autowired
    public UserController(@Qualifier("selfUserService") UserService userService, RestTemplate restTemplate) {
        this.userService = userService;
        this.restTemplate = restTemplate;
    }

    @GetMapping()
    public Optional<List<User>> getAllUsers() {
        return userService.getAllUsers();
    };

    @GetMapping("/{id}")
    public Optional<User> getSingleUser(@PathVariable("id") Long id) throws UserNotFoundException {
        return userService.getSingleUser(id);
    }

    @PostMapping()
    public ResponseEntity<User> addANewUser(@RequestBody User user) {
        ResponseEntity<User> response = new ResponseEntity<>(
                userService.addANewUser(user), HttpStatus.OK);
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> updateAUser(@PathVariable Long id,@RequestBody User user) throws UserNotFoundException {
        ResponseEntity<User> response = new ResponseEntity<>(userService.updateUser(id,user), HttpStatus.OK);
        return response;
    }


}
