package com.gagful.service;

import com.gagful.dto.PasswordDTO;
import com.gagful.dto.UserDTO;

import javax.validation.Valid;
import java.util.List;


public interface UserService {
    List<UserDTO> findAll();

    UserDTO save(@Valid UserDTO userDTO);

    UserDTO findById(String userId);

    UserDTO findByUsername(String username);

    UserDTO update(@Valid UserDTO userDTO);

    UserDTO changePassword(String userId, @Valid PasswordDTO passwordDTO);
}
