package com.AjoPay.AjoPay.controller;

import com.AjoPay.AjoPay.dto.request.UserRequestDto;
import com.AjoPay.AjoPay.model.User;
import com.AjoPay.AjoPay.service.UserService;
import com.AjoPay.AjoPay.service.serviceImplementation.UserServiceImplementation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URL;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    // save User
    @PostMapping("/users")
    public User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    // get All the user in databases

    @GetMapping("/users")
    public List<User> fetAllUsers(){
        return userService.fetAllUsers();

    }

    // fecth Data by Id

    @GetMapping("/users{id}")
    public User fetUserById(@PathVariable("id") Long userId){

        return userService.fetUserById(userId);
    }

    // delete data by Id
    @DeleteMapping("/users{id}")
    public String deleteUserById(@PathVariable("id")  Long userId){
         userService.deleteUserById(userId);
        return "user delete Successfully";
    }

    // update data in the database
    @PutMapping("users{id}")
    public User UpdateUser(@PathVariable("id") Long userId, @RequestBody User user){
        return userService.UpdateUser(userId, user);
    }

    // fetch user by firstName
    @GetMapping("users/firstName{firstName}")
    public User fetchByFirstName(@PathVariable("firstName ") String firstName){

        return userService.fetchByFirstName(firstName);
    }

}
