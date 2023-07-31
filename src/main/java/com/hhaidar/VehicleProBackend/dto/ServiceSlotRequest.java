package com.hhaidar.VehicleProBackend.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class ServiceSlotRequest {
    String startTime;
    String endTime;
    Integer maxCapacity;
}
