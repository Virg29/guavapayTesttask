package com.guavapay.testtask.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Entity
@Table(name = "parcels")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Parcel {

    @Id
    @GeneratedValue
    private UUID id;

    private String daddress;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "courier_id")
    private Courier courier;

    private float lat;

    private float lng;

    @Enumerated(EnumType.STRING)
    private ParcelStatus status;
}
