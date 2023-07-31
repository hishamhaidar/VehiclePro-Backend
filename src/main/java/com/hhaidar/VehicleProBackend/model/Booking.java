package com.hhaidar.VehicleProBackend.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue
    private Integer bookingID;
    private Integer slotID;

    @Enumerated(EnumType.STRING)
    private Status bookingStatus;

    public Booking(Integer slotID){
        this.slotID=slotID;
        this.bookingStatus= Status.PENDING;
    }
}
