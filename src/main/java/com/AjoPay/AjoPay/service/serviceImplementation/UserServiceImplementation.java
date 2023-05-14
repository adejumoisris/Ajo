package com.AjoPay.AjoPay.service.serviceImplementation;

import com.AjoPay.AjoPay.exceptions.UserNotFoundException;
import com.AjoPay.AjoPay.model.User;
import com.AjoPay.AjoPay.repository.UserRepo;
import com.AjoPay.AjoPay.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    private UserRepo userRepo;
    // construction injection
    @Override
    public User saveUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public List<User> fetAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User fetUserById(Long userId) throws UserNotFoundException {
        Optional<User> user =  userRepo.findById(userId);
        // if user is not present throw this exception
        if(!user.isPresent()){
            throw new UserNotFoundException(" User not found");
        }
        // if usser is present throw this value
        return user.get();
    }

    @Override
    public void deleteUserById(Long userId) {
         userRepo.deleteById(userId);
    }
    @Override
    public User UpdateUser(Long userId, User user) {
        User userDB = userRepo.findById(userId).get();
        if (Objects.nonNull(user.getFirstName()) && !"".equalsIgnoreCase(user.getFirstName())){
            userDB.setFirstName(user.getFirstName());
        }
        if (Objects.nonNull(user.getLastName()) && !"".equalsIgnoreCase(user.getLastName())){
            userDB.setLastName(user.getLastName());
        }
        if (Objects.nonNull(user.getEmail()) && !"".equalsIgnoreCase(user.getEmail())){
            userDB.setEmail(user.getEmail());
        }
        return userRepo.save(userDB);
    }

    @Override
    public User fetchByFirstName(String firstName) {
        return userRepo.findByFirstName(firstName);
    }


    // creating all users




}
