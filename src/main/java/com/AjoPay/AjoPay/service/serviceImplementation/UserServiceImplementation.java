package com.AjoPay.AjoPay.service.serviceImplementation;

import com.AjoPay.AjoPay.dto.request.UserRequestDto;
import com.AjoPay.AjoPay.dto.response.UserResponse;
import com.AjoPay.AjoPay.exceptions.CustomException;
import com.AjoPay.AjoPay.exceptions.UserNotFoundException;
import com.AjoPay.AjoPay.model.User;
import com.AjoPay.AjoPay.repository.UserRepo;
import com.AjoPay.AjoPay.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService, UserDetailsService {

    private final UserRepo userRepo;
     private final PasswordEncoder passwordEncoder;
    // construction injection
    @Override
    public UserResponse createUser(UserRequestDto request) {

        log.info("service:: about setting");
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        user.setPassword(encodedPassword);
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
        userToUpdate.setLastName(request.getLastName());
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


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info(" userService loadUserByUserName -email :: [{}] ::", email);
        User user = userRepo.findByfirstName(email)
                .orElseThrow(
                        ()->{
                            throw new CustomException("incorrect username or password ");
                        }
                );
        Collection<SimpleGrantedAuthority> authorities =
                Collections.singleton(new SimpleGrantedAuthority(user.getRole()));
        return new org.springframework.security.core.userdetails.User(user.getFirstName(), user.getPassword(), authorities);
    }


    // creating all users




}
