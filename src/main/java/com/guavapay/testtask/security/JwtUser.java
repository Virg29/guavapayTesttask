package com.guavapay.testtask.security;

import com.guavapay.testtask.entity.Admin;
import com.guavapay.testtask.entity.BaseUser;
import com.guavapay.testtask.entity.Courier;
import com.guavapay.testtask.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@RequiredArgsConstructor
@Slf4j
public class JwtUser implements UserDetails {

    private final BaseUser user;
    private final String login;
    private final String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.user instanceof Admin)return Set.of(new SimpleGrantedAuthority("admin"));
        if(this.user instanceof User)return Set.of(new SimpleGrantedAuthority("user"));
        if(this.user instanceof Courier)return Set.of(new SimpleGrantedAuthority("courier"));
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
