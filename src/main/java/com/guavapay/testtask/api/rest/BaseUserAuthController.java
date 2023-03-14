package com.guavapay.testtask.api.rest;

import com.guavapay.testtask.api.dto.AuthRequestDto;
import com.guavapay.testtask.api.dto.AuthResponseDto;
import com.guavapay.testtask.entity.BaseUser;
import com.guavapay.testtask.security.jwt.JwtTokenProvider;
import com.guavapay.testtask.service.BaseService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class BaseUserAuthController {
    public AuthResponseDto authUser(AuthRequestDto authRequestDto, BaseService service, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider){
        try{
            String login = authRequestDto.getLogin();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login,authRequestDto.getPassword()));
            BaseUser user = service.getBaseUserByLogin(login);

            if(user==null){
                throw new UsernameNotFoundException("Username not found");
            }

            String token = jwtTokenProvider.createToken(login);

            return new AuthResponseDto(login,token);
        }catch (AuthenticationException e){
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
