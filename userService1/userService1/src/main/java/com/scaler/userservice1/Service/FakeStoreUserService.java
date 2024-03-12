package com.scaler.userservice1.Service;

import com.scaler.userservice1.DTO.FakeStoreUserDTO;
import com.scaler.userservice1.Models.Address;
import com.scaler.userservice1.Models.GeoLocation;
import com.scaler.userservice1.Models.Name;
import com.scaler.userservice1.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class FakeStoreUserService implements UserService{

    private final RestTemplate restTemplate;

    @Autowired
    public FakeStoreUserService(RestTemplate restTemplate) {this.restTemplate = restTemplate;}

    private User converFakeStoreUsertoUser(FakeStoreUserDTO fakeStoreProductDTO) {
        User user = new User();
        user.setId(fakeStoreProductDTO.getId());
        user.setEmail(fakeStoreProductDTO.getEmail());
        user.setPassword(fakeStoreProductDTO.getPassword());
        user.setUsername(fakeStoreProductDTO.getUsername());
        user.setName(new Name());
        user.getName().setFirstName(fakeStoreProductDTO.getName().getFirstName());
        user.getName().setLastName(fakeStoreProductDTO.getName().getLastName());
        user.setAddress(new Address());
//        user.getAddress().setCity(fakeStoreProductDTO.getAddress().getCity());
//        user.getAddress().setNumber(fakeStoreProductDTO.getAddress().getNumber());
//        user.getAddress().setStreet(fakeStoreProductDTO.getAddress().getStreet());
//        user.getAddress().setZipcode(fakeStoreProductDTO.getAddress().getZipcode());
//        user.getAddress().setGeoLocation(new GeoLocation());
//        user.getAddress().getGeoLocation().setLat(fakeStoreProductDTO.getAddress().getGeoLocation().getLat());
//        user.getAddress().getGeoLocation().setLongitude(56.2);

        return user;
    }
//    @Override
//    public List<User> getAllUsers() {
//        FakeStoreUserDTO[] users = restTemplate.getForObject("https://fakestoreapi.com/users", FakeStoreUserDTO[].class);
//        List<User> users1 = new ArrayList<>();
//        for(FakeStoreUserDTO dto: users) {
//            users1.add(converFakeStoreUsertoUser(dto));
//        }
//        return users1;
//    }

    @Override
    public Optional<List<User>> getAllUsers() {
        return Optional.empty();
    }

    @Override
    public Optional<User> getSingleUser(Long id) {
        return Optional.empty();
    }

//    @Override
//    public Optional<User> getSingleUser(Long id) {
//        FakeStoreUserDTO userDTO = restTemplate.getForObject(
//                "https://fakestoreapi.com/users/" + id, FakeStoreUserDTO.class
//        );
//        return converFakeStoreUsertoUser(userDTO);
//    }

    @Override
    public User addANewUser(User user) {
        return user;
    }

    @Override
    public User updateUser(Long id,User user) {
        FakeStoreUserDTO fakeuser = new FakeStoreUserDTO();
        fakeuser.setId(id);
        fakeuser.setName(new Name());
        fakeuser.getName().setLastName(user.getName().getLastName());
        fakeuser.getName().setFirstName(user.getName().getFirstName());
        fakeuser.setPassword(user.getPassword());
        fakeuser.setEmail(user.getEmail());
        fakeuser.setPhone(user.getPhone());
        fakeuser.setUsername(user.getUsername());
        fakeuser.setAddress(new Address());
//        fakeuser.getAddress().setCity(user.getAddress().getCity());
//        fakeuser.getAddress().setStreet(user.getAddress().getStreet());
//        fakeuser.getAddress().setZipcode(user.getAddress().getZipcode());
//        fakeuser.getAddress().setNumber(user.getAddress().getNumber());
        fakeuser.getAddress().setGeoLocation(new GeoLocation());
        fakeuser.getAddress().getGeoLocation().setLongitude(99.99);
        fakeuser.getAddress().getGeoLocation().setLat(99.99);
        return converFakeStoreUsertoUser(fakeuser);
    }

    @Override
    public User deleteUser(Long id) {
        return null;
    }


}
