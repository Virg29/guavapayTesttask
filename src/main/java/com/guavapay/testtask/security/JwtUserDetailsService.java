package com.guavapay.testtask.security;

import com.guavapay.testtask.entity.User;
import com.guavapay.testtask.security.jwt.JwtUser;
import com.guavapay.testtask.security.jwt.JwtUserFactory;
import com.guavapay.testtask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByLogin(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        JwtUser jwtUser = JwtUserFactory.create(user);
        return jwtUser;
    }
}
