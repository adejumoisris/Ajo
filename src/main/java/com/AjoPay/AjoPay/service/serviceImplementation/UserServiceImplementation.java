package com.AjoPay.AjoPay.service.serviceImplementation;

import com.AjoPay.AjoPay.repository.UserRepo;
import com.AjoPay.AjoPay.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {
    private final UserRepo userRepo; // construction injection


}
