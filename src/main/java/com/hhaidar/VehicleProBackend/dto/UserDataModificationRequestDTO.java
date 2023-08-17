package com.hhaidar.VehicleProBackend.dto;

import com.hhaidar.VehicleProBackend.model.Role;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class UserDataModificationRequestDTO {
    private  String username;
    private  String email;
    private  Role role;
}
