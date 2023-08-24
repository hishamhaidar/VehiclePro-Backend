package com.hhaidar.VehicleProBackend.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class RegistrationRequestDTO {
    private  String username;
    private  String email;
    private  String password;
}
