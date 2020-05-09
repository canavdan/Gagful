package com.gagful.service.impl;

import com.gagful.dto.PasswordDTO;
import com.gagful.dto.UserDTO;
import com.gagful.entity.User;
import com.gagful.exception.PasswordNotMatchException;
import com.gagful.exception.UserNotFoundException;
import com.gagful.mapper.Mapper;
import com.gagful.repository.UserRepository;
import com.gagful.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Service
@Validated
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final Mapper mapper;

    @Override
    public List<UserDTO> findAll() {
        log.info("Find all users:");
        return mapper.mapList(userRepository.findAll(), UserDTO.class);
    }

    @Override
    public UserDTO save(@Valid UserDTO userDTO) {
        log.info("Saving user: " + userDTO.toString());
        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        User user = mapper.mapEntity(userDTO, User.class);
        return mapper.mapEntity(userRepository.save(user), UserDTO.class);
    }

    @Override
    public UserDTO findById(String userId) {
        log.info("Get user by id: " + userId);
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User not found with userId: " + userId));
        return mapper.mapEntity(user, UserDTO.class);
    }

    @Override
    public UserDTO findByUsername(String username) {
        log.info("Get user by username: " + username);
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException("User not found with username: " + username));
        return mapper.mapEntity(user, UserDTO.class);
    }

    @Override
    public UserDTO update(@Valid UserDTO userDTO) {
        log.info("Update user with username: " + userDTO.getUsername());
        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        User user = mapper.mapEntity(userDTO, User.class);
        return mapper.mapEntity(userRepository.save(user), UserDTO.class);
    }

    @Override
    public UserDTO changePassword(String userId, @Valid PasswordDTO passwordDTO) {
        UserDTO userDTO = this.findById(userId);
        if (bCryptPasswordEncoder.matches(passwordDTO.getOldPassword(), userDTO.getPassword())) {
            userDTO.setPassword(bCryptPasswordEncoder.encode(passwordDTO.getNewPassword()));
            User user = mapper.mapEntity(userDTO, User.class);
            return mapper.mapEntity(userRepository.save(user), UserDTO.class);
        }
        throw new PasswordNotMatchException("Password not match.");
    }


}
