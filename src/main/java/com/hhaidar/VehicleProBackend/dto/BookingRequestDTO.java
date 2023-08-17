package com.hhaidar.VehicleProBackend.dto;

import lombok.*;

@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequestDTO {
    private  String clientName;
    private  String clientEmail;
    private Integer vehicleID;
    private String vehicleProblem;
}
