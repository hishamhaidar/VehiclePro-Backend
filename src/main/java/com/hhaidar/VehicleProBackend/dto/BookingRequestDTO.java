package com.hhaidar.VehicleProBackend.dto;

import lombok.*;

@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class BookingRequestDTO {
    private final String clientName;
    private final String clientEmail;
}
