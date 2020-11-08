package com.finnplay.demo.service;

import com.finnplay.demo.model.dto.PasswordDTO;
import com.finnplay.demo.model.dto.UserDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private UserDTO testUser;

    @BeforeEach
    public void setup() {
        this.testUser = UserDTO.builder()
                .username("Test User")
                .password("123132")
                .birthday("")
                .email("")
                .firstName("")
                .lastName("")
                .build();
    }

    @Test
    public void createUserTest() {

        userService.create(this.testUser);

        UserDTO savedUser = userService.findById(this.testUser.getId());
        Assertions.assertNotNull(savedUser);
        Assertions.assertEquals("Test User", savedUser.getUsername());
        Assertions.assertTrue(bCryptPasswordEncoder.matches("123132", savedUser.getPassword()));

        savedUser.setEmail("ee@ee.ee");
        savedUser.setPassword("222222");
        userService.update(savedUser);

        savedUser = userService.findByUsername("Test User");
        Assertions.assertEquals("ee@ee.ee", savedUser.getEmail());
        Assertions.assertTrue(bCryptPasswordEncoder.matches("123132", savedUser.getPassword()));

        PasswordDTO passwordDTO = new PasswordDTO();
        passwordDTO.setNewPassword("111111");

        userService.updatePassword(passwordDTO, this.testUser.getId());

        savedUser = userService.findByUsername("Test User");
        Assertions.assertTrue(bCryptPasswordEncoder.matches("111111", savedUser.getPassword()));
    }

}
