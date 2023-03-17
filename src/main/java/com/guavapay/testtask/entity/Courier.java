package com.guavapay.testtask.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "couriers")
@Data

//@RequiredArgsConstructor
//@AllArgsConstructor
public class Courier extends BaseUser {

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(255) default 'FREE'")
    private CourierStatus status;
    public Courier() {
    }

    public Courier(String login, String password) {
        super(login, password);
    }
}