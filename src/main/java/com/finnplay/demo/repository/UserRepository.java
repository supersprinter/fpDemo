package com.finnplay.demo.repository;

import com.finnplay.demo.model.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;


public interface UserRepository extends CrudRepository<User, UUID>
{
    Optional<User> findByUsername(String username);

}
