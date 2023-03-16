package com.guavapay.testtask.api.rest;

import com.guavapay.testtask.api.dto.AuthRequestDto;
import com.guavapay.testtask.security.jwt.JwtTokenProvider;
import com.guavapay.testtask.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/v1/admin")
@Api(tags = {"admin"})
@Slf4j
public class AdminController extends BaseUserAuthController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;

//    @PostMapping(path = "/login")
    @ApiOperation(value = "Endpoint for admin logining")
    public ResponseEntity login(@RequestBody AuthRequestDto authRequestDto){
        log.info("Login",authRequestDto.getLogin());
        return ResponseEntity.ok(authUser(authRequestDto,adminService,authenticationManager,jwtTokenProvider));
    }
    @GetMapping(path="/login")
    public String another(){
        return "hello";
    }





}
