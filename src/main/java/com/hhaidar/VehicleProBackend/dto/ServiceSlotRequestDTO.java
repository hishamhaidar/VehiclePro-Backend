package com.hhaidar.VehicleProBackend.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class ServiceSlotRequestDTO {
    String startTime;
    String endTime;
    Integer maxCapacity;
}
