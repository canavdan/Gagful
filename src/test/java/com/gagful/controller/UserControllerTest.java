package com.gagful.controller;

import com.gagful.base.BaseRequest;
import com.gagful.dto.UserDTO;
import com.gagful.mapper.Mapper;
import com.gagful.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Mapper mapper;

    @MockBean
    private UserServiceImpl userService;

    @Test
    @WithMockUser(authorities = {"Admin", "Member"})
    void saveUser_thenReturns200() throws Exception {
        BaseRequest baseRequest=new BaseRequest();
        UserDTO user = new UserDTO();
        user.setUsername("test");
        user.setPassword("pass");
        user.setFirstName("Firstname");
        user.setLastName("Lastname");
        user.setEmail("deneme@gmail.com");
        user.setAge(20);
        baseRequest.setData(user);
        mockMvc.perform(post("/api/v1/user")
                .content(baseRequest.toString())
                .contentType("application/json"))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(authorities = {"Admin", "Member"})
    void getUser_thenReturns200() throws Exception {
        mockMvc.perform(get("/api/v1/admin/secure/user/users")
                .contentType("application/json"))
                .andExpect(status().isOk());
    }
}