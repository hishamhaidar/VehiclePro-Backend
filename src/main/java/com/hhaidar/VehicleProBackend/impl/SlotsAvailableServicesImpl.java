package com.hhaidar.VehicleProBackend.impl;

import com.hhaidar.VehicleProBackend.dto.ServiceSlotRequestDTO;
import com.hhaidar.VehicleProBackend.model.GarageUser;
import com.hhaidar.VehicleProBackend.model.ServiceSlots;
import com.hhaidar.VehicleProBackend.repository.ServicesSlotsrepo;
import com.hhaidar.VehicleProBackend.repository.UserRepo;
import com.hhaidar.VehicleProBackend.service.SlotsAvailableServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SlotsAvailableServicesImpl implements SlotsAvailableServices {
    private final UserRepo userRepo;
    private final ServicesSlotsrepo slotsrepo;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Override
    public ResponseEntity<String> createSlot(Integer userID, ServiceSlotRequestDTO slotRequest) {
        Optional<GarageUser> slotCreator = userRepo.findById(userID);
        if (!slotCreator.isPresent())
            return ResponseEntity.badRequest().body("GarageUser of id: "+userID+" Does not exist");
        GarageUser creator = slotCreator.get();
        try {

            LocalDateTime startTime = LocalDateTime.parse(slotRequest.getStartTime(), formatter);
            LocalDateTime endTime = LocalDateTime.parse(slotRequest.getEndTime(), formatter);
            slotsrepo.save(new ServiceSlots(userID, creator.getUserEmail(), startTime, endTime, slotRequest.getMaxCapacity()));
            return ResponseEntity.ok("Slot saved succesfully");
        }
        catch (DateTimeParseException e){
            return ResponseEntity.badRequest().body("Invalid date-time format. Use the format: yyyy-MM-dd h:mm AM/PM");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Error occured.please retry");
        }
    }
    @Override
    public ResponseEntity<String> editSlot(Integer userID,Integer slotID, ServiceSlotRequestDTO slotRequest) {
        Optional<ServiceSlots> currSlot = slotsrepo.findById(slotID);
        Optional<GarageUser> modifierUser = userRepo.findById(userID);
        if (!currSlot.isPresent())
            return ResponseEntity.badRequest().body("Slot does not exist");
        ServiceSlots modified_slot = currSlot.get();
        if (!modifierUser.get().getUserEmail().equals(modified_slot.getCreatedBy()))
            return ResponseEntity.badRequest().body("Please edit slots created by you ");

        try {

            LocalDateTime startTime = LocalDateTime.parse(slotRequest.getStartTime(), formatter);
            LocalDateTime endTime = LocalDateTime.parse(slotRequest.getEndTime(), formatter);
            modified_slot.setStartTime(startTime);
            modified_slot.setEndTime(endTime);
            modified_slot.setMaxCapacity(slotRequest.getMaxCapacity());
            slotsrepo.save(modified_slot);
            return ResponseEntity.ok(modified_slot.toString());
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body("Invalid date-time format. Use the format: yyyy-MM-dd h:mm AM/PM");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error occured.please retry");
        }


    }
    @Override
    public ResponseEntity<String> deleteSlot(Integer slotID) {
        try {
            slotsrepo.deleteById(slotID);
            return ResponseEntity.ok("Slot of ID: "+slotID+" was deleted succesfully");
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body("Error happened ,please retry");
        }

    }
    @Override
    public ResponseEntity<ArrayList<ServiceSlots>> searchByUserID(Integer userID) {
        try {
            ArrayList<ServiceSlots> slots = slotsrepo.findAllByUserID(userID);
            return ResponseEntity.ok(slots);
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(new ArrayList<>());
        }
    }
    @Override
    public ResponseEntity<ArrayList<ServiceSlots>> showAllSlots() {
        return ResponseEntity.ok((ArrayList<ServiceSlots>) slotsrepo.findAll());
    }
}
