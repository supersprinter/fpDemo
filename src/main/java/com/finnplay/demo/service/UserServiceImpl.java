package com.finnplay.demo.service;

import com.finnplay.demo.exception.UserNotFoundException;
import com.finnplay.demo.model.dto.PasswordDTO;
import com.finnplay.demo.model.dto.UserDTO;
import com.finnplay.demo.model.entity.User;
import com.finnplay.demo.repository.UserRepository;
import com.finnplay.demo.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    @Transactional
    public void create(UserDTO userDTO) {

        userDTO.setPassword(encodePassword(userDTO.getPassword()));

        User user = getUserByDto(userDTO);

        userRepository.save(user);

        userDTO.setId(user.getId());
    }


    @Override
    @Transactional
    public void update(UserDTO userDTO) {

        Optional<User> user = userRepository.findById(userDTO.getId());
        if (user.isEmpty()) {
            throw new UserNotFoundException(String.format("Cannot find user %s/%s ", userDTO.getId(), userDTO.getUsername()));
        }

        updateUserEntityWithNewDataExceptPassword(userDTO, user.get());

        userRepository.save(user.get());
    }

    @Override
    @Transactional
    public void updatePassword(PasswordDTO passwordDTO, UUID userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new UserNotFoundException(String.format("Cannot find user %s ", userId));
        }
        user.get().setPassword(encodePassword(passwordDTO.getNewPassword()));

        userRepository.save(user.get());
    }

    @Override
    public UserDTO findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(this::getDtoByUser).orElse(null);
    }

    @Override
    public UserDTO findById(UUID userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()) {
            throw new UserNotFoundException(String.format("Cannot find user %s ", userId));
        }
        return getDtoByUser(user.get());
    }


    private void updateUserEntityWithNewDataExceptPassword(UserDTO userDTO, User user) {
        userDTO.setPassword(user.getPassword());

        BeanUtils.copyProperties(userDTO, user); // rewrite in case of performance issues

        user.setBirthday(getInstantByString(userDTO.getBirthday()));
    }


    private String encodePassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }


    private UserDTO getDtoByUser(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .birthday(Utils.instantToStringConverter(user.getBirthday()))
                .build();
    }


    private User getUserByDto(UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .username(userDTO.getUsername())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .birthday(getInstantByString(userDTO.getBirthday()))
                .password(userDTO.getPassword())
                .build();

    }


    private Instant getInstantByString(String birthdayStr) {
        if(birthdayStr.isEmpty()) {
            return null;
        }
        Instant result = null;
        try {
            result = Utils.stringToInstantConverter(birthdayStr);
        } catch (ParseException e) {
            logger.error(String.format("Cannot parse birthday %s ", birthdayStr));
        }
        return result;
    }

}