package com.AjoPay.AjoPay.service;

import com.AjoPay.AjoPay.model.User;

import java.util.List;

public interface UserService {
   public  User saveUser(User user);

  public  List<User> fetAllUsers();

  public   User fetUserById(Long userId);

   public   void deleteUserById(Long userId);

  public   User UpdateUser(Long userId, User user);

   public User fetchByFirstName(String firstName);


    // Creating All users


}
