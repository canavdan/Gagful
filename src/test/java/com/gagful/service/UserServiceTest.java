package com.gagful.service;

import com.gagful.dto.UserDTO;
import com.gagful.entity.User;
import com.gagful.exception.UserNotFoundException;
import com.gagful.mapper.Mapper;
import com.gagful.repository.UserRepository;
import com.gagful.service.UserService;
import com.gagful.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RestClientTest(UserService.class)
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private Mapper mapper;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;


    @Before
    public void init() {
        user = new User();
        user.setUsername("userDTO");
        user.setId(UUID.randomUUID().toString());
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));

    }

    @Test(expected = UserNotFoundException.class)
    public void findByUsername_thenGetUserNotFoundException() {
        when(userRepository.findById(any())).thenReturn(Optional.empty());
        userService.findByUsername("NotUsername");
    }

    @Test(expected = UserNotFoundException.class)
    public void findById_thenGetUserNotFoundException() {
        when(userRepository.findById(any())).thenReturn(Optional.empty());
        userService.findById("UserId");
    }

    @Test
    public void getUser() {
        UserDTO userDTO = userService.findByUsername("username1");
        Assert.assertNotNull(userDTO.getUsername());
        Assert.assertEquals(user.getUsername(), userDTO.getUsername());
        Assert.assertNotNull(userDTO.getFirstName());
        Assert.assertEquals(user.getFirstName(), userDTO.getFirstName());
        Assert.assertNotNull(userDTO.getLastName());
        Assert.assertEquals(user.getLastName(), userDTO.getLastName());
    }
}
