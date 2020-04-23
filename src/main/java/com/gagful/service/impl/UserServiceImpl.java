package com.gagful.service.impl;

import com.gagful.dto.UserDTO;
import com.gagful.entity.User;
import com.gagful.exception.UserNotFoundException;
import com.gagful.mapper.Mapper;
import com.gagful.repository.UserRepository;
import com.gagful.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Service
@Validated
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Mapper mapper;

    @Override
    public List<UserDTO> findAll() {
        return mapper.mapList(userRepository.findAll(),UserDTO.class);
    }

    @Override
    public UserDTO save(@Valid UserDTO userDTO) {
        User user = mapper.mapEntity(userDTO,User.class);
        return mapper.mapEntity(userRepository.save(user),UserDTO.class);
    }

    @Override
    public UserDTO findById(String userId) {
        User user= userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User not found with userId: " + userId));
        return mapper.mapEntity(user,UserDTO.class);
    }

    @Override
    public UserDTO findByUsername(String username) {
        User user= userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException("User not found with username: " + username));
        return mapper.mapEntity(user,UserDTO.class);
    }

    @Override
    public UserDTO update(@Valid UserDTO userDTO) {
        User user=mapper.mapEntity(userDTO,User.class);
        return mapper.mapEntity(userRepository.save(user),UserDTO.class);
    }

}
