package com.hhaidar.VehicleProBackend.controller;

import com.hhaidar.VehicleProBackend.dto.ServiceSlotRequest;
import com.hhaidar.VehicleProBackend.model.ServiceSlots;
import com.hhaidar.VehicleProBackend.service.SlotsAvailableServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/slots")
public class ServiceSlotsController {
    private final SlotsAvailableServices slotsServices;
    @PreAuthorize("hasAnyRole(SERVICE_MANAGER,GARAGE_OWNER)")
    @PostMapping("/create/{userID}")
    public ResponseEntity<String> createSlot(@PathVariable Integer userID, @RequestBody ServiceSlotRequest slotRequest){
        return slotsServices.createSlot(userID,slotRequest);
    }
    @PreAuthorize("hasAnyRole(SERVICE_MANAGER,GARAGE_OWNER)")
    @PutMapping("/edit/{userID}/{slotID}")
    public ResponseEntity<String> editSlot(@PathVariable Integer userID,@PathVariable Integer slotID, @RequestBody ServiceSlotRequest slotRequest){
        return slotsServices.editSlot(userID,slotID,slotRequest);
    }

    @PreAuthorize("hasAnyRole(SERVICE_MANAGER,GARAGE_OWNER)")
    @DeleteMapping("/delete/{slotID}")
    public  ResponseEntity<String> deleteSlot(@PathVariable Integer slotID){
        return slotsServices.deleteSlot(slotID);
    }
    @GetMapping("/search/{userID}")
    public  ResponseEntity<ArrayList<ServiceSlots>> searchByUserID(@PathVariable Integer userID){
        return slotsServices.searchByUserID(userID);
    }
    @GetMapping("/search/all")
    public  ResponseEntity<ArrayList<ServiceSlots>> getAllSlots(){
        return slotsServices.showAllSlots();
    }

}
