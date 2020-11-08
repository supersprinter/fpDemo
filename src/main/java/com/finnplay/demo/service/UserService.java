package com.finnplay.demo.service;

import com.finnplay.demo.exception.UserNotFoundException;
import com.finnplay.demo.model.dto.PasswordDTO;
import com.finnplay.demo.model.dto.UserDTO;

import java.util.UUID;

public interface UserService {

    void create(UserDTO user);

    /**
     * Update all user data except password
     * @param user {@link UserDTO}
     */
    void update(UserDTO user);

    void updatePassword(PasswordDTO password, UUID userId);

    /**
     * Find user by username
     * @param username {@link String}
     * @return null or {@link UserDTO}
     */
    UserDTO findByUsername(String username);

    /**
     * Find user by user ID
     * @throws UserNotFoundException
     * @param userId {@link UUID}
     * @return {@link UserDTO}
     */
    UserDTO findById(UUID userId);

}
