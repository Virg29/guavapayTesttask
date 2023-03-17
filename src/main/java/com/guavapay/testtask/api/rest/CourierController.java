package com.guavapay.testtask.api.rest;

import com.guavapay.testtask.api.dto.MarkDeliveredRequestDto;
import com.guavapay.testtask.api.dto.SetCoordsOfParcelRequestDto;
import com.guavapay.testtask.api.dto.ViewOrderDetailsRequestDto;
import com.guavapay.testtask.api.dto.ViewOrderDetailsResponseDto;
import com.guavapay.testtask.entity.*;
import com.guavapay.testtask.service.CourierService;
import com.guavapay.testtask.service.ParcelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


//Can see the details of a delivery order;
@RestController
@RequestMapping(value = "/api/v1/courier")
@Tag(name = "courier")
public class CourierController {
    @Autowired
    private ParcelService parcelService;
    @Autowired
    private CourierService courierService;

    @PostMapping("/viewDetails")
    @Operation(summary = "View details of parcel order")
    public ResponseEntity cancelOrder(@RequestBody ViewOrderDetailsRequestDto requestDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        Courier currentUser = (Courier) courierService.getBaseUserByLogin(login);

        Parcel parcel = parcelService.getParcelById(requestDto.getId());

        if(parcel == null) return ResponseEntity.badRequest().body("Parcel with provided id not found");
        if(!parcel.getCourier().equals(currentUser.getLogin())) return ResponseEntity.badRequest().body("Its not your parcel");

        return ResponseEntity.ok(new ViewOrderDetailsResponseDto(parcel.getStatus(), parcel.getLat(), parcel.getLng(), parcel.getCourier().getLogin()));
    }
    @PostMapping(path = "/markDelivered")
    @Operation(summary = "Marks parcel delivered")
    public ResponseEntity markDelivered(@RequestBody MarkDeliveredRequestDto requestDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        Courier currentCourier = (Courier) courierService.getBaseUserByLogin(login);

        UUID parcelid = requestDto.getParcelid();
        Parcel parcel = parcelService.getParcelById(parcelid);
        if(!parcel.getCourier().getLogin().equals(currentCourier.getLogin())) return ResponseEntity.badRequest().body("It's not your order.");
        parcel.setStatus(ParcelStatus.DELIVERED);
        parcelService.updateParcel(parcelid,parcel);

        currentCourier.setStatus(CourierStatus.FREE);
        courierService.updateCourier(currentCourier.getId(),currentCourier);
        return ResponseEntity.ok("");
    }
    @GetMapping(path = "/myOrders")
    @Operation(summary = "Get all orders assigned to authorized courier")
    public ResponseEntity myOrders(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        Courier currentCourier = (Courier) courierService.getBaseUserByLogin(login);
        List<Parcel> parcels = parcelService.getAllParcelsByCourier(currentCourier);
        return ResponseEntity.ok(parcels);
    }
    @PostMapping(path = "/setCoords")
    @Operation(summary = "Set coordinates of parcel")
    public ResponseEntity setCoords(@RequestBody SetCoordsOfParcelRequestDto requestDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        Courier currentCourier = (Courier) courierService.getBaseUserByLogin(login);

        UUID parcelid = requestDto.getParcelid();
        float lat = requestDto.getLat();
        float lng = requestDto.getLng();

        Parcel parcel = parcelService.getParcelById(parcelid);
        if(parcel == null) return ResponseEntity.badRequest().body("Parcel not found");
        if(!parcel.getCourier().getLogin().equals(currentCourier.getLogin())) return ResponseEntity.badRequest().body("It's not your order.");

        parcel.setLat(lat);
        parcel.setLng(lng);

        parcelService.updateParcel(parcelid,parcel);
        return ResponseEntity.ok("");
    }


}
