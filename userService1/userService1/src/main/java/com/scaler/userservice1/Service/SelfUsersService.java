package com.scaler.userservice1.Service;

import com.scaler.userservice1.Exceptions.UserNotFoundException;
import com.scaler.userservice1.Models.Address;
import com.scaler.userservice1.Models.GeoLocation;
import com.scaler.userservice1.Models.Name;
import com.scaler.userservice1.Models.User;
import com.scaler.userservice1.Repository.AddressRepository;
import com.scaler.userservice1.Repository.GeoLocationRepository;
import com.scaler.userservice1.Repository.NameRepository;
import com.scaler.userservice1.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfUserService")
@Primary
public class SelfUsersService implements UserService {

    private UserRepository userRepository;
    private AddressRepository addressRepository;
    private NameRepository nameRepository;

    private GeoLocationRepository geoLocationRepository;

    @Autowired
    public SelfUsersService(UserRepository userRepository, AddressRepository addressRepository, NameRepository nameRepository, GeoLocationRepository geoLocationRepository) {
        this.userRepository = userRepository;;
        this.addressRepository = addressRepository;
        this.nameRepository = nameRepository;
        this.geoLocationRepository = geoLocationRepository;
    }

    @Override
    public Optional<List<User>> getAllUsers() {
        Optional<List<User>> usersList = Optional.of(userRepository.findAll());
        return usersList;
    }

    @Override
    public Optional<User> getSingleUser(Long id) throws UserNotFoundException {
        Optional<User> user = (userRepository.findById(id));
        if(user.isEmpty()) throw new UserNotFoundException("User with id: "+id+" doesn't exist");
        return user;
    }
    @Override
    public User addANewUser(User user) {
//        User user1 = new User();
//        user1.setName(user.getName());
//        user1.setUsername(user.getUsername());
//        user1.setPassword(user.getPassword());
//        user1.setPhone(user.getPhone());
////        user1.setId(user.getId());
//        user1.setDeleted(user.isDeleted());
//        user1.setGeneratedAt(user.getGeneratedAt());
//        user1.setEmail(user.getEmail());
//        user1.setName(new Name());
//        user1.getName().setFirstName(user.getName().getFirstName());
//        user1.getName().setLastName(user.getName().getLastName());
//        user1.setAddress(new Address());
//        user1.getAddress().setNumber(user.getAddress().getNumber());
//        user1.getAddress().setCity(user.getAddress().getCity());
//        user1.getAddress().setStreet(user.getAddress().getStreet());
////        user1.getAddress().setId(user.getAddress().getId());
//        user1.getAddress().setZipcode(user.getAddress().getZipcode());
//        user1.getAddress().setGeneratedAt(user.getAddress().getGeneratedAt());
//        user1.getAddress().setLastUpdatedAt(user.getAddress().getLastUpdatedAt());
//        user1.getAddress().setGeoLocation(new GeoLocation());
//        user1.getAddress().getGeoLocation().setLat(user.getAddress().getGeoLocation().getLat());
//        user1.getAddress().getGeoLocation().setLongitude(user.getAddress().getGeoLocation().getLongitude());

        Optional<Name> nameOptional = nameRepository.findByFirstName(user.getName().getFirstName());
        if(nameOptional.isEmpty()) {
            Name name = nameRepository.save(user.getName());
        } else {
            user.setName(nameOptional.get());
        }

        Optional<GeoLocation> geoLocationOptional = geoLocationRepository.findByLat(user.getAddress().getGeoLocation().getLat());
        if(geoLocationOptional.isEmpty()) {
            GeoLocation geoLocation = geoLocationRepository.save(user.getAddress().getGeoLocation());
        } else {
            user.getAddress().setGeoLocation(geoLocationOptional.get());
        }

        Optional<Address> optionalAddress = addressRepository.findByZipcode(user.getAddress().getZipcode());
        if(optionalAddress.isEmpty()) {
            Address address = addressRepository.save(user.getAddress());
        } else {
            user.setAddress(optionalAddress.get());
        }


        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User user) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isEmpty()) throw new UserNotFoundException("User with id: " +id+" dosen't exist.");

        User userReturned = userOptional.get();

        if(user.getName().getFirstName() != null) {userReturned.getName().setFirstName(user.getName().getFirstName());}
        if(user.getName().getLastName() != null) {userReturned.getName().setLastName(user.getName().getLastName());}

        if(user.getAddress().getGeoLocation().getLongitude() != null) {
            userReturned.getAddress().getGeoLocation().setLongitude(user.getAddress().getGeoLocation().getLongitude());
        }
        if(user.getAddress().getGeoLocation().getLat() != null) {
            userReturned.getAddress().getGeoLocation().setLat(user.getAddress().getGeoLocation().getLat());
        }

        if(user.getAddress().getCity()!=null) {
            userReturned.getAddress().setCity(user.getAddress().getCity());
        }
        if(user.getAddress().getZipcode()!=null) {
            userReturned.getAddress().setZipcode(user.getAddress().getZipcode());
        }
        if(user.getAddress().getStreet()!=null) {
            userReturned.getAddress().setStreet(user.getAddress().getStreet());
        }
        if(user.getAddress().getNumber()!=null) {
            userReturned.getAddress().setNumber(user.getAddress().getNumber());
        }



        return userRepository.save(userReturned);
    }

    @Override
    public User deleteUser(Long id) {
        Optional<User> deleteuser = userRepository.findById(id);
        deleteuser.get().setDeleted(true);
        return deleteuser.get();
    }


}
