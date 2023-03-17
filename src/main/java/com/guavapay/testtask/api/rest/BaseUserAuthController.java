package com.guavapay.testtask.api.rest;

import com.guavapay.testtask.api.dto.AuthRequestDto;
import com.guavapay.testtask.api.dto.AuthResponseDto;
import com.guavapay.testtask.entity.BaseUser;
import com.guavapay.testtask.entity.User;
import com.guavapay.testtask.security.JwtUtilities;
import com.guavapay.testtask.service.AdminService;
import com.guavapay.testtask.service.CourierService;
import com.guavapay.testtask.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/v1/auth")
//@RequestMapping(value = "/")
@Api(tags = {"admin"})
@Slf4j
public class BaseUserAuthController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;
    @Autowired
    private CourierService courierService;
    @Autowired
    private JwtUtilities jwtUtilities;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @PostMapping(path = "/login")
    @ApiOperation(value = "Endpoint for admin logining")
    public ResponseEntity login(@RequestBody AuthRequestDto authRequestDto){
//        log.info("{}".formatted(authUser(authRequestDto)));
        try{
            String login = authRequestDto.getLogin();

            log.info("Login a %s".formatted(login));
            log.info("Password a %s".formatted(authRequestDto.getPassword()));

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    login,
                    authRequestDto.getPassword()
                )
            );
            BaseUser user = null;
            if(user == null) user = userService.getBaseUserByLogin(login);
            if(user == null) user = courierService.getBaseUserByLogin(login);
            if(user == null) user = adminService.getBaseUserByLogin(login);

            if(user==null){
                throw new UsernameNotFoundException("Username not found");
            }

            String token = jwtUtilities.createToken(login);

            return ResponseEntity.ok(new AuthResponseDto(login,token));
        }catch (Exception e){
            log.info(e.toString());
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping(path = "/user/register")
    @ApiOperation(value = "User registration endpoint")
    public ResponseEntity registerUser(@RequestBody AuthRequestDto authRequestDto){
        String login = authRequestDto.getLogin();
        BaseUser user = null;
        if(user == null) user = userService.getBaseUserByLogin(login);
        if(user == null) user = courierService.getBaseUserByLogin(login);
        if(user == null) user = adminService.getBaseUserByLogin(login);
        if(user == null) userService.createUser(new User(authRequestDto.getLogin(),bCryptPasswordEncoder.encode(authRequestDto.getPassword())));
        if(user != null) ResponseEntity.badRequest().body("Account with this login already exists");
        return ResponseEntity.ok(authRequestDto);
    }

}
