package com.AjoPay.AjoPay.repository;

import com.AjoPay.AjoPay.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
