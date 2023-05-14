package com.AjoPay.AjoPay.repository;

import com.AjoPay.AjoPay.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    public User findByFirstName (String firstName);

}
