package com.AjoPay.AjoPay.service.serviceImplementation;

import com.AjoPay.AjoPay.dto.request.UserRequestDto;
import com.AjoPay.AjoPay.dto.response.UserResponse;
import com.AjoPay.AjoPay.exceptions.UserNotFoundException;
import com.AjoPay.AjoPay.model.User;
import com.AjoPay.AjoPay.repository.UserRepo;
import com.AjoPay.AjoPay.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Slf4j
@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    private UserRepo userRepo;
    // construction injection
    @Override
    public UserResponse createUser(UserRequestDto request) {

        log.info("service:: about setting");
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setGender(request.getGender());
        user.setPhoneNumber(request.getPhoneNumber());
        log.info("about saving");

        User saveUser = userRepo.save(user);
        log.info("save uuser");
        return new UserResponse(
                saveUser.getFirstName(),
                saveUser.getLastName(),
                saveUser.getEmail(),
                saveUser.getPassword()

                );
    }

    @Override
    public UserResponse fetchUserById(Long userId) {
        User saveUser = userRepo.findById(userId).get();
        return new UserResponse(saveUser.getFirstName(), saveUser.getLastName(), saveUser.getEmail(), saveUser.getPassword());
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepo.deleteById(userId);

    }

    @Override
    public UserResponse UpdateUserById(Long userId, UserRequestDto request) {
        User userToUpdate = userRepo.findById(userId).get();
        userToUpdate.setFirstName(request.getFirstName());
        userToUpdate.setPassword(request.getPassword());

        User saveUser = userRepo.save(userToUpdate);

        return new UserResponse(
                saveUser.getFirstName(),
                saveUser.getLastName(),
                saveUser.getEmail(),
                saveUser.getPassword()
                );

    }


    @Override
    public List<User> fetAllUsers() {
        return userRepo.findAll();
    }






    // creating all users




}
