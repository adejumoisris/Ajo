package com.AjoPay.AjoPay.service;

import com.AjoPay.AjoPay.exceptions.UserNotFoundException;
import com.AjoPay.AjoPay.model.User;

import java.util.List;

public interface UserService {
   public  User saveUser(User user);

  public  List<User> fetAllUsers();

  public   User fetUserById(Long userId) throws UserNotFoundException;

   public   void deleteUserById(Long userId);

  public   User UpdateUser(Long userId, User user);

   public User fetchByFirstName(String firstName);


    // Creating All users


}
