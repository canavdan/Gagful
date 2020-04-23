package com.gagful.service;

import com.gagful.dto.UserDTO;
import com.gagful.entity.User;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


public interface UserService {
    List<UserDTO> findAll();
    UserDTO save(@Valid UserDTO userDTO);
    UserDTO findById(String userId);
    UserDTO findByUsername(String username);
    UserDTO update(@Valid UserDTO userDTO);
}
