package com.guavapay.testtask.api.dto;

import com.guavapay.testtask.entity.ParcelStatus;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ViewOrderDetailsResponseDto {
    private final ParcelStatus parcelStatus;
    private final float lat;
    private final float lng;
    private final String courierName;
}
