package com.AjoPay.AjoPay.repository;

import com.AjoPay.AjoPay.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> getByusername(String username);
    User findByAccountNumber(String  accountNumber);

}
