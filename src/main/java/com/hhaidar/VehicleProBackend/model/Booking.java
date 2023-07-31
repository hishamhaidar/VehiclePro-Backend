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
    private String clientFullName;
    private String clientEmail;
    @Enumerated(EnumType.STRING)
    private Status bookingStatus;

    public Booking(Integer slotID, String clientFullName, String clientEmail) {
        this.slotID = slotID;
        this.clientFullName = clientFullName;
        this.clientEmail = clientEmail;
        this.bookingStatus=Status.PENDING;
    }
}
