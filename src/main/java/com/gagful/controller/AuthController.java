package com.gagful.controller;

import com.gagful.base.BaseController;
import com.gagful.base.BaseRequest;
import com.gagful.base.BaseResponse;
import com.gagful.constant.APIConstants;
import com.gagful.dto.UserDTO;
import com.gagful.dto.request.JwtRequest;
import com.gagful.entity.User;
import com.gagful.service.UserService;
import com.gagful.service.impl.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping(APIConstants.VERSION + "/public/auth")
public class AuthController extends BaseController<User> {


    private final UserService userService;

    private final AuthService authService;

    @PostMapping(value = "/", produces = APIConstants.CHARSET)
    public ResponseEntity<BaseResponse> save(HttpServletRequest request, @RequestBody BaseRequest<UserDTO> userDTO) {
        UserDTO userDTOSaved = userService.save(userDTO.getData());
        return responseUtil.successResponse(userDTOSaved);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<BaseResponse> auth(@RequestBody BaseRequest<JwtRequest> jwtRequest) {

        String username = jwtRequest.getData().getUsername();
        String password = jwtRequest.getData().getPassword();

        UserDTO user = authService.findUserByUsernameAndPassword(username, password);
        return responseUtil.successResponse(user);
    }
}
