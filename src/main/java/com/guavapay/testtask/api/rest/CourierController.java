package com.guavapay.testtask.api.rest;

import com.guavapay.testtask.api.dto.AuthRequestDto;
import com.guavapay.testtask.security.jwt.JwtTokenProvider;
import com.guavapay.testtask.service.CourierService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/courier")
@Api(tags = {"courier"})
public class CourierController extends BaseUserAuthController{
    @Autowired
    private CourierService courierService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(path = "/login")
    public ResponseEntity login(@RequestBody AuthRequestDto authRequestDto){
        return ResponseEntity.ok(authUser(authRequestDto,courierService,authenticationManager,jwtTokenProvider));
    }
}
