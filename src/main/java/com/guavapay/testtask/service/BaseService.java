package com.guavapay.testtask.service;

import com.guavapay.testtask.entity.BaseUser;

public interface BaseService {
    BaseUser getBaseUserByLogin(String login);
}
