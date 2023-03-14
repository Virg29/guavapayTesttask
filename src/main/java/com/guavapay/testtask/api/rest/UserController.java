package com.guavapay.testtask.api.rest;

import com.guavapay.testtask.api.dto.AuthRequestDto;
import com.guavapay.testtask.security.jwt.JwtTokenProvider;
import com.guavapay.testtask.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/user")
@Api(tags = {"user"})
public class UserController extends BaseUserAuthController{
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(path = "/login")
    public ResponseEntity login(@RequestBody AuthRequestDto authRequestDto){
        return ResponseEntity.ok(authUser(authRequestDto,userService,authenticationManager,jwtTokenProvider));
    }
}
