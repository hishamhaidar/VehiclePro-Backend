package com.hhaidar.VehicleProBackend.controller;

import com.hhaidar.VehicleProBackend.dto.ServiceSlotRequestDTO;
import com.hhaidar.VehicleProBackend.model.ServiceSlots;
import com.hhaidar.VehicleProBackend.impl.SlotsAvailableServicesImpl;
import com.hhaidar.VehicleProBackend.service.SlotsAvailableServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/slots")
@CrossOrigin("http://localhost:3000")
public class ServiceSlotsController {
    private final SlotsAvailableServices slotsServices;

    @PostMapping("/create/{userID}")
    public ResponseEntity<String> createSlot(@PathVariable Integer userID, @RequestBody ServiceSlotRequestDTO slotRequest){
        return slotsServices.createSlot(userID,slotRequest);
    }

    @PutMapping("/edit/{userID}/{slotID}")
    public ResponseEntity<String> editSlot(@PathVariable Integer userID,@PathVariable Integer slotID, @RequestBody ServiceSlotRequestDTO slotRequest){
        return slotsServices.editSlot(userID,slotID,slotRequest);
    }


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
