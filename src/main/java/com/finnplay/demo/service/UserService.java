package com.finnplay.demo.service;

import com.finnplay.demo.dto.UserDTO;
import com.finnplay.demo.model.User;

public interface UserService {
    void save(UserDTO user);

    UserDTO findByUsername(String username);
}
