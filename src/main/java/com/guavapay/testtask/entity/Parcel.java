package com.guavapay.testtask.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.UUID;


@Entity
@Table(name = "parcels")
@Data
@NoArgsConstructor
//@AllArgsConstructor
public class Parcel {
    public Parcel(String daddress) {
        this.daddress = daddress;
    }

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
    @Column(columnDefinition = "varchar(255) default 'WAITING'")
    private ParcelStatus status;
}
