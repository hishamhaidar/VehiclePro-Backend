package com.hhaidar.VehicleProBackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class ServiceSlots {
    @Id
    @GeneratedValue
    private Integer slotID;

    private Integer userID;

    private String createdBy;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer maxCapacity;
    private Integer currCapacity;

    public ServiceSlots(Integer userID, String createdBy, LocalDateTime startTime, LocalDateTime endTime, Integer maxCapacity) {
        this.userID = userID;
        this.createdBy = createdBy;
        this.startTime = startTime;
        this.endTime = endTime;
        this.maxCapacity = maxCapacity;
        this.currCapacity=maxCapacity;
    }
}
