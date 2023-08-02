package com.hhaidar.VehicleProBackend.service;

import com.hhaidar.VehicleProBackend.dto.ServiceSlotRequestDTO;
import com.hhaidar.VehicleProBackend.model.ServiceSlots;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

public interface SlotsAvailableServices {
    ResponseEntity<String> createSlot(Integer userID, ServiceSlotRequestDTO slotRequest);
    ResponseEntity<String> editSlot(Integer userID,Integer slotID, ServiceSlotRequestDTO slotRequest);
    ResponseEntity<String> deleteSlot(Integer slotID);
    ResponseEntity<ArrayList<ServiceSlots>> searchByUserID(Integer userID);
    public ResponseEntity<ArrayList<ServiceSlots>> showAllSlots();
}
