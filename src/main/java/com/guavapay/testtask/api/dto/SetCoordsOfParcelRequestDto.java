package com.guavapay.testtask.api.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class SetCoordsOfParcelRequestDto {
    private UUID parcelid;
    private float lat;
    private float lng;
}
