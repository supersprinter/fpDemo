package com.finnplay.demo.repository;

import com.finnplay.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer>
{
    User findByUsername(String username);
}
