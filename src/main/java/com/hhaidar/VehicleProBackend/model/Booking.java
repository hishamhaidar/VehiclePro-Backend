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
    private Integer vehicleID;
    private String vehicleProblem;

    public Booking(Integer slotID, String clientFullName, String clientEmail,Integer vehicleID,String vehicleProblem) {
        this.slotID = slotID;
        this.clientFullName = clientFullName;
        this.clientEmail = clientEmail;
        this.vehicleID=vehicleID;
        this.vehicleProblem=vehicleProblem;
        this.bookingStatus=Status.PENDING;
    }
}
