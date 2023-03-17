package com.guavapay.testtask.api.rest;

import com.guavapay.testtask.api.dto.*;
import com.guavapay.testtask.entity.Parcel;
import com.guavapay.testtask.entity.ParcelStatus;
import com.guavapay.testtask.entity.User;
import com.guavapay.testtask.service.ParcelService;
import com.guavapay.testtask.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/user")
@Tag(name = "user")
public class UserController{

    @Autowired
    private ParcelService parcelService;
    @Autowired
    private UserService userService;

    @PostMapping("/createOrder")
    @Operation(summary = "Creates parcel delivery order")
    public ResponseEntity createOrder(@RequestBody CreateParcelDeliveryOrderRequestDto requestDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        User currentUser = (User) userService.getBaseUserByLogin(login);

        Parcel parcel = new Parcel(requestDto.getDAddress());
        parcel.setUser(currentUser);
        parcel.setStatus(ParcelStatus.WAITING);
        Parcel createdParcel = parcelService.createParcel(parcel);
        return ResponseEntity.ok(new CreateParcelDeliveryOrderResponseDto(createdParcel.getId()));
    }

    @PostMapping("/changeDest")
    @Operation(summary = "Changing destination address of parcel")
    public ResponseEntity changeAdress(@RequestBody ChangeDestinationOfParcelRequestDto requestDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        User currentUser = (User) userService.getBaseUserByLogin(login);

        Parcel parcel = parcelService.getParcelById(requestDto.getId());

        if(parcel == null) return ResponseEntity.badRequest().body("Parcel with provided id not found");
        if(!parcel.getUser().equals(currentUser.getLogin())) return ResponseEntity.badRequest().body("Its not your parcel");
        if(parcel.getStatus()!=ParcelStatus.WAITING) return ResponseEntity.badRequest().body("Parcell already is in processing, u cant do this now.");

        parcel.setDaddress(requestDto.getAddress());

        parcelService.updateParcel(parcel.getId(),parcel);

        return ResponseEntity.ok(new ChangeDestinationOfParcelResponseDto());
    }

    @PostMapping("/cancelOrder")
    @Operation(summary = "Cancelling parcel order")
    public ResponseEntity cancelOrder(@RequestBody CancelOrderRequestDto requestDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        User currentUser = (User) userService.getBaseUserByLogin(login);

        Parcel parcel = parcelService.getParcelById(requestDto.getId());

        if(parcel == null) return ResponseEntity.badRequest().body("Parcel with provided id not found");
        if(!parcel.getUser().equals(currentUser.getLogin())) return ResponseEntity.badRequest().body("Its not your parcel");
        if(parcel.getStatus()!=ParcelStatus.WAITING) return ResponseEntity.badRequest().body("Parcell already is in processing, u cant do this now.");

        parcel.setStatus(ParcelStatus.CANCELED);

        parcelService.updateParcel(parcel.getId(),parcel);

        return ResponseEntity.ok(new CancelOrderResponseDto());
    }

    @PostMapping("/viewDetails")
    @Operation(summary = "View details of parcel order")
    public ResponseEntity cancelOrder(@RequestBody ViewOrderDetailsRequestDto requestDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        User currentUser = (User) userService.getBaseUserByLogin(login);

        Parcel parcel = parcelService.getParcelById(requestDto.getId());

        if(parcel == null) return ResponseEntity.badRequest().body("Parcel with provided id not found");
        if(!parcel.getUser().equals(currentUser.getLogin())) return ResponseEntity.badRequest().body("Its not your parcel");

        return ResponseEntity.ok(new ViewOrderDetailsResponseDto(parcel.getStatus(), parcel.getLat(), parcel.getLng(), parcel.getCourier().getLogin()));
    }

    @GetMapping("/viewOrders")
    @Operation(summary = "View all placed orders by current user")
    public ResponseEntity viewOrders(@RequestBody ViewOrderDetailsRequestDto requestDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        User currentUser = (User) userService.getBaseUserByLogin(login);

        List<Parcel> parcels = parcelService.getAllParcelsByUser(currentUser);

        return ResponseEntity.ok(parcels);
    }
}
