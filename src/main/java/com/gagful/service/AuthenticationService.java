package com.gagful.service;

import com.gagful.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.validation.Valid;

public interface AuthenticationService extends UserDetailsService {

    UserDTO findUserByUserName(String username);

    UserDTO save(@Valid UserDTO userDTO);
}
