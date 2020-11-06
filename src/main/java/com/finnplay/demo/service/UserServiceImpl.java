package com.finnplay.demo.service;

import com.finnplay.demo.dto.UserDTO;
import com.finnplay.demo.model.User;
import com.finnplay.demo.repository.UserRepository;
import com.finnplay.demo.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(UserDTO userDTO) {
        if(userDTO.getId() == null) { // New user
            userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
            User user = getUserByDto(userDTO, true);
            userRepository.save(user);
        } else { // Old user
            User user = getUserByDto(userDTO, false);
            User dbUserData = userRepository.findByUsername(userDTO.getUsername());
            user.setPassword(dbUserData.getPassword());
            userRepository.save(user);
        }
    }

    @Override
    public UserDTO findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user == null ? null : getDtoByUser(user);
    }

    private User getUserByDto(UserDTO userDTO, boolean isPasswordNeeded) {
        User.UserBuilder ub = User.builder()
                .id(userDTO.getId())
                .username(userDTO.getUsername())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .birthday(getInstantByString(userDTO.getBirthday()));
        if (isPasswordNeeded) {
            return ub.password(userDTO.getPassword()).build();
        } else {
            return ub.build();
        }
    }

    private UserDTO getDtoByUser(User user) {
        UserDTO userDTO = UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .birthday(Utils.InstantToStringConverter(user.getBirthday()))
                .build();
        return userDTO;
    }

    private Instant getInstantByString(String birthdayStr) {
        if(birthdayStr.isEmpty()) {
            return null;
        }
        Instant result = null;
        try {
            result = Utils.StringToInstantConverter(birthdayStr);
        } catch (ParseException e) {
            logger.error(String.format("Cannot parse birthday %s ", birthdayStr));
        }
        return result;
    }

}