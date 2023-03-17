package com.guavapay.testtask.api.rest;

import com.guavapay.testtask.api.dto.AssignToCourierRequestDto;
import com.guavapay.testtask.api.dto.ChangeOrderStatusRequestDto;
import com.guavapay.testtask.api.dto.CreateCourierRequestDto;
import com.guavapay.testtask.api.dto.SetCoordsOfParcelRequestDto;
import com.guavapay.testtask.entity.Courier;
import com.guavapay.testtask.entity.CourierStatus;
import com.guavapay.testtask.entity.Parcel;
import com.guavapay.testtask.entity.ParcelStatus;
import com.guavapay.testtask.service.CourierService;
import com.guavapay.testtask.service.ParcelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping(value = "/api/v1/admin")
@Tag(name = "admin")
@Slf4j
public class AdminController{

    @Autowired
    private CourierService courierService;
    @Autowired
    private ParcelService parcelService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping(path = "/couriers")
    public ResponseEntity couriers(){
        List<Courier> couriers = courierService.getAllCouriers();
        return ResponseEntity.ok(couriers);
    }
    @PostMapping(path = "/setCoords")
    @Operation(summary = "Set coordinates of parcel")
    public ResponseEntity setCoords(@RequestBody SetCoordsOfParcelRequestDto requestDto){
        UUID parcelid = requestDto.getParcelid();
        float lat = requestDto.getLat();
        float lng = requestDto.getLng();

        Parcel parcel = parcelService.getParcelById(parcelid);
        if(parcel == null) return ResponseEntity.badRequest().body("Parcel not found");

        parcel.setLat(lat);
        parcel.setLng(lng);

        parcelService.updateParcel(parcelid,parcel);
        return ResponseEntity.ok("");
    }
    @PostMapping(path = "/assignToCourier")
    @Operation(summary = "View all parcel orders")
    public ResponseEntity viewAll(@RequestBody AssignToCourierRequestDto requestDto){
        UUID courierid = requestDto.getCourierid();
        UUID parcelid = requestDto.getParcelid();

        Parcel parcel = parcelService.getParcelById(parcelid);
        Courier courier = courierService.getCourierById(courierid);
        if(parcel==null) return ResponseEntity.badRequest().body("Parcel not found");
        if(courier==null) return ResponseEntity.badRequest().body("Courier not found");
        if(courier.getStatus()!= CourierStatus.FREE) return ResponseEntity.badRequest().body("Courier on delivery now.");

        courier.setStatus(CourierStatus.ONDELIVERY);
        courierService.updateCourier(courierid,courier);

        parcel.setCourier(courier);
        parcelService.updateParcel(parcelid,parcel);

        return ResponseEntity.ok("");
    }
    @GetMapping(path = "/viewAll")
    @Operation(summary = "View all parcel orders")
    public ResponseEntity viewAll(){
        List<Parcel> parcels = parcelService.getAllParcels();
        return ResponseEntity.ok(parcels);
    }
    @PostMapping(path = "/changeStatus")
    @Operation(summary = "Change status of delivery order")
    public ResponseEntity changeStatus(@RequestBody ChangeOrderStatusRequestDto requestDto){
        UUID id = requestDto.getId();
        ParcelStatus parcelStatus = requestDto.getStatus();

        Parcel parcel = parcelService.getParcelById(id);
        if(parcel==null) return ResponseEntity.badRequest().body("Parcel not found");
        parcel.setStatus(parcelStatus);
        parcelService.updateParcel(id,parcel);

        return ResponseEntity.ok("");
    }

    @PostMapping(path = "/createCourier")
    @Operation(summary = "Creates courier account with provided credentials")
    public ResponseEntity createCourier(@RequestBody CreateCourierRequestDto requestDto){
        String login = requestDto.getLogin();
        String password = requestDto.getPassword();
        Courier courier = new Courier(login,passwordEncoder.encode(password));
        courier.setStatus(CourierStatus.FREE);
        courierService.createCourier(courier);
        return ResponseEntity.ok("");
    }
}
