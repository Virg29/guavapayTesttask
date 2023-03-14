package com.guavapay.testtask.security.jwt;

import com.guavapay.testtask.entity.BaseUser;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class JwtUserFactory {
    public static JwtUser create(BaseUser user){
        return new JwtUser(user, user.getId(), user.getLogin(), user.getPassword());
    }
}
