package com.gagful.controller;

import com.gagful.base.BaseController;
import com.gagful.base.BaseRequest;
import com.gagful.base.BaseResponse;
import com.gagful.constant.APIConstants;
import com.gagful.dto.PasswordDTO;
import com.gagful.dto.UserDTO;
import com.gagful.entity.User;
import com.gagful.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(APIConstants.VERSION + "/admin/secure/user")
public class UserController extends BaseController<User> {


    private final UserService userService;

    @PreAuthorize("hasRole('Admin')")
    @GetMapping(value = "/users", produces = APIConstants.CHARSET)
    public ResponseEntity<BaseResponse> findAll(HttpServletRequest request) {
        List<UserDTO> userList = userService.findAll();
        return responseUtil.successListResponse(userList, 1, 10);
    }

    @PostMapping(value = "/", produces = APIConstants.CHARSET)
    public ResponseEntity<BaseResponse> save(HttpServletRequest request, @RequestBody BaseRequest<UserDTO> userDTO) {
        UserDTO userDTOSaved = userService.save(userDTO.getData());
        return responseUtil.successResponse(userDTOSaved);
    }

    @PreAuthorize("hasRole('Admin')")
    @GetMapping(value = "/{userId}", produces = APIConstants.CHARSET)
    public ResponseEntity<BaseResponse> findById(@PathVariable String userId) {
        UserDTO userDTO = userService.findById(userId);
        return responseUtil.successResponse(userDTO);

    }

    @GetMapping(value = "/", produces = APIConstants.CHARSET)
    public ResponseEntity<BaseResponse> findByUsername(@RequestParam(value = "username") String username) {
        UserDTO userDTO = userService.findByUsername(username);
        return responseUtil.successResponse(userDTO);
    }

    @PutMapping(value = "/{userId}", produces = APIConstants.CHARSET)
    public ResponseEntity<BaseResponse> update(@PathVariable String userId, @RequestBody BaseRequest<UserDTO> userDTO) {
        UserDTO userDTOUpdated = userService.update(userDTO.getData());
        return responseUtil.successResponse(userDTOUpdated);
    }


    @PutMapping(value = "/changePass", produces = APIConstants.CHARSET)
    public ResponseEntity<BaseResponse> changePassword(@RequestParam(value = "username") String userId, @RequestBody BaseRequest<PasswordDTO> passwordDTO) {
        UserDTO userDTO = userService.changePassword(userId, passwordDTO.getData());
        return responseUtil.successResponse(userDTO);
    }
}
