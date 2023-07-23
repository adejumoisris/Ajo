package com.AjoPay.AjoPay.controller;

import com.AjoPay.AjoPay.dto.request.LoginRequest;
import com.AjoPay.AjoPay.dto.request.UserRequestDto;
import com.AjoPay.AjoPay.dto.response.ApiResponse;
import com.AjoPay.AjoPay.dto.response.TokenResponse;
import com.AjoPay.AjoPay.dto.response.UserResponse;
import com.AjoPay.AjoPay.service.UserService;
import com.AjoPay.AjoPay.utilis.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "auth")
@Slf4j
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    @PostMapping("/login")

    public ResponseEntity<?> AuthenticationAndGetToken(@RequestBody LoginRequest request){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(),request.getPassword()));
        if (authentication.isAuthenticated()){
            TokenResponse response = SecurityUtils.generateToken(authentication);
            return ResponseEntity.status(200).body(ApiResponse.builder().statusCode(true).data(response).message("Authenticated").build());
        }else {
            throw new UsernameNotFoundException("invalid user request");
        }
    }





    @PostMapping(path = "/signup")
    public ResponseEntity<ApiResponse<UserResponse>> createAccount(@RequestBody UserRequestDto request){
        log.info("create user call for first name: {}", request.getFirstName());
        UserResponse response = userService.createUser(request);
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setData(response);
        apiResponse.setStatusCode(true);
        apiResponse.setMessage("create user Successfully");
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }
}
