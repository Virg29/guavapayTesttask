package com.guavapay.testtask.api.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CancelOrderRequestDto {
    private UUID id;
}
