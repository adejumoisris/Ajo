package com.AjoPay.AjoPay.controller;

import com.AjoPay.AjoPay.dto.request.UserRequestDto;
import com.AjoPay.AjoPay.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor // always go for construction injection
public class UserController {
    private final UserService userService;  // construction injection
    @PostMapping(path = "/register")
    public ResponseEntity<String> saveUser(@Valid UserRequestDto requestDto){
        log.info("email is needed " , requestDto.getEmail());
        return null;
    }
}
