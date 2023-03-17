package com.guavapay.testtask.api.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ChangeDestinationOfParcelRequestDto {
    private UUID id;
    private String address;
}
