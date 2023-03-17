package com.guavapay.testtask.api.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
public class CreateParcelDeliveryOrderResponseDto {
    private final UUID uuid;
}
