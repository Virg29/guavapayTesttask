package com.guavapay.testtask.api.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class AssignToCourierRequestDto {
    private UUID courierid;
    private UUID parcelid;
}
