package com.KoloPay.KoloPay.repository;

import com.KoloPay.KoloPay.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
