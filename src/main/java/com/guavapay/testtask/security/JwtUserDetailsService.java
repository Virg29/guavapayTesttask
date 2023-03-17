package com.guavapay.testtask.security;

import com.guavapay.testtask.entity.BaseUser;
import com.guavapay.testtask.service.AdminService;
import com.guavapay.testtask.service.CourierService;
import com.guavapay.testtask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


//@Component
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private CourierService courierService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BaseUser user = null;
        if (user == null) user = userService.getBaseUserByLogin(username);
        if (user == null) user = adminService.getBaseUserByLogin(username);
        if (user == null) user = courierService.getBaseUserByLogin(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        JwtUser jwtUser = JwtUserFactory.create(user);
        return jwtUser;
    }
}

