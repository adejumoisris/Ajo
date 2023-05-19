package com.AjoPay.AjoPay.controller;

import com.AjoPay.AjoPay.dto.request.UserRequestDto;
import com.AjoPay.AjoPay.dto.response.ApiResponse;
import com.AjoPay.AjoPay.dto.response.UserResponse;
import com.AjoPay.AjoPay.exceptions.UserNotFoundException;
import com.AjoPay.AjoPay.model.User;
import com.AjoPay.AjoPay.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(path = "/signup")
    public ResponseEntity<ApiResponse<UserResponse>> createAccount(@RequestBody UserRequestDto request){
        log.info("create user call for first name: {}", request.getFirstName());
        UserResponse response = userService.createUser(request);
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(response);
        apiResponse.setStatusCode("00");
        apiResponse.setMessage("creat user Successfully");
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }
    // get All the user in databases

    @GetMapping("/users")
    public List<User> fetAllUsers(){

        return userService.fetAllUsers();

    }

    // fecth Data by Id

    @GetMapping(path = "{userId}/user")
    public ResponseEntity<Object> fetchUserById (@PathVariable Long userId){
        UserResponse foundUser = userService.fetchUserById(userId);
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(foundUser);
        apiResponse.setStatusCode("00");
        apiResponse.setMessage("fetch User");
        return ResponseEntity.status(200).body(apiResponse);
    }

    // delete data by Id
    @DeleteMapping("/{userId}/user")
    public ResponseEntity<Object> deleteUserById(@PathVariable Long userId){
        userService.deleteUserById(userId);
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setData("user deleted");
        apiResponse.setStatusCode("00");
        return ResponseEntity.status(202).body(apiResponse);
    }


    // update data in the database
    @PutMapping(path = "{userId}/user")
    public ResponseEntity<Object> UpdateUserById(@PathVariable Long userId, @RequestBody UserRequestDto request){
        UserResponse response = userService.UpdateUserById(userId , request);
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(response);
        apiResponse.setStatusCode("00");
        apiResponse.setMessage("updates successfully");
        return ResponseEntity.status(202).body(apiResponse);

    }


    // fetch user by firstName


}
