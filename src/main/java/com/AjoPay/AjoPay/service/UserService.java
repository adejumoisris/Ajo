package com.AjoPay.AjoPay.service;

import com.AjoPay.AjoPay.dto.request.TransferRequest;
import com.AjoPay.AjoPay.dto.request.UserRequestDto;
import com.AjoPay.AjoPay.dto.response.BankResponse;
import com.AjoPay.AjoPay.dto.response.UserResponse;
import com.AjoPay.AjoPay.exceptions.UserNotFoundException;
import com.AjoPay.AjoPay.model.User;

import java.util.List;

public interface UserService {
    public   UserResponse createUser(UserRequestDto request);

   public UserResponse fetchUserById(Long userId);

  public  List<User> fetAllUsers();

   public void deleteUserById(Long userId);

   public UserResponse UpdateUserById(Long userId, UserRequestDto request);

    String verifyAccount(String token);

    // Transferring Money from one Account to Another
     public BankResponse transfer(TransferRequest request);


    // Creating All users


}
