package com.gagful.service.impl;

import com.gagful.constant.ExceptionConstant;
import com.gagful.dto.UserDTO;
import com.gagful.entity.Role;
import com.gagful.entity.User;
import com.gagful.exception.InvalidUserCredentialsException;
import com.gagful.exception.UserNotFoundException;
import com.gagful.mapper.Mapper;
import com.gagful.repository.UserRepository;
import com.gagful.service.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Validated
@Slf4j
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;

    private final Mapper mapper;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final RedisService redisService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        log.info("Trying to login username: " + username);
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException(ExceptionConstant.USER_NOT_FOUND + username));
        List<Role> role = new ArrayList<>();
        role.add(user.getRole());
        List<GrantedAuthority> grantedAuthorities = role.stream().map(r -> {
            return new SimpleGrantedAuthority(r.getName());
        }).collect(Collectors.toList());
        redisService.set("username", user.getUsername());
        redisService.set("uId", user.getId());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }


    public UserDTO findUserByUserName(String username) {

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException("User not found with username: " + username));
        return mapper.mapEntity(user, UserDTO.class);
    }

    public UserDTO findUserByUsernameAndPassword(String username, String password) {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException("User not found with username: " + username));
        if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
            loadUserByUsername(username);
            return mapper.mapEntity(user, UserDTO.class);
        } else {
            throw new InvalidUserCredentialsException("Username and/or password are incorrect exception");
        }

    }


    public UserDTO save(@Valid UserDTO userDTO) {
        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        User user = mapper.mapEntity(userDTO, User.class);
        return mapper.mapEntity(userRepository.save(user), UserDTO.class);
    }
}
