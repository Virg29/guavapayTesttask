package com.guavapay.testtask.api.dto;

import lombok.Data;

@Data
public class CreateCourierRequestDto {
    private String login;
    private String password;
}
