package com.guavapay.testtask.security.jwt;

import com.guavapay.testtask.entity.User;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class JwtUserFactory {
    public static JwtUser create(User user){
        return new JwtUser(user.getId(), user.getLogin(), user.getPassword());
    }
}
