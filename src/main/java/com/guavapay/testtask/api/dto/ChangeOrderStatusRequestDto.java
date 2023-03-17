package com.guavapay.testtask.api.dto;

import com.guavapay.testtask.entity.ParcelStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class ChangeOrderStatusRequestDto {
    private UUID id;
    private ParcelStatus status;
}
