package com.hhaidar.VehicleProBackend.service;

import com.hhaidar.VehicleProBackend.model.Booking;
import com.hhaidar.VehicleProBackend.model.ServiceSlots;
import com.hhaidar.VehicleProBackend.model.Status;
import com.hhaidar.VehicleProBackend.repository.BookingRepo;
import com.hhaidar.VehicleProBackend.repository.ServicesSlotsrepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServices {
    private final BookingRepo bookingRepo;
    private final ServicesSlotsrepo slotsrepo;

    public ResponseEntity<String> createBooking(Integer slotID) {
        Optional<ServiceSlots> tempSlot = slotsrepo.findById(slotID);
        if (!tempSlot.isPresent()) {
            return ResponseEntity.badRequest().body("Requested slot does not exist");
        }
        ServiceSlots requiredSlot = tempSlot.get();
        if (requiredSlot.getCurrCapacity() <= 0)
            return ResponseEntity.badRequest().body("Sorry the slot is full,please wait or try different slot");
        bookingRepo.save(new Booking(slotID));
        return ResponseEntity.ok("Your booking is pending now,we will send you an update soon!");

    }

    public ResponseEntity<String> confirmBooking(Integer bookingID) {
        Optional<Booking> tempBooking = bookingRepo.findById(bookingID);
        if (!tempBooking.isPresent()) {
            return ResponseEntity.badRequest().body("Requested Booking does not exist");
        }
        Booking bookingToConfirm = tempBooking.get();
        ServiceSlots slot = slotsrepo.findById(bookingToConfirm.getSlotID()).get();
        int currSlotCapacity = slot.getCurrCapacity();
        if (currSlotCapacity >=1) {
            slot.setCurrCapacity(currSlotCapacity-1);
            slotsrepo.save(slot);
            bookingToConfirm.setBookingStatus(Status.CONFIRMED);
            bookingRepo.save(bookingToConfirm);
            //TODO : SEND CONFIRMATION MAIL
            return ResponseEntity.ok("Booking is confirmed");
        }
        return ResponseEntity.badRequest().body("Slot full , no more possible bookings");
    }

    public ResponseEntity<String> denyBooking(Integer bookingID) {
        Optional<Booking> tempBooking = bookingRepo.findById(bookingID);
        if (!tempBooking.isPresent()) {
            return ResponseEntity.badRequest().body("Requested Booking does not exist");
        }
        Booking bookingToDeny = tempBooking.get();
        bookingToDeny.setBookingStatus(Status.DENIED);
        bookingRepo.save(bookingToDeny);
        ServiceSlots slot = slotsrepo.findById(bookingToDeny.getSlotID()).get();
        int currSlotCapacity = slot.getCurrCapacity();
        currSlotCapacity = (currSlotCapacity >= slot.getMaxCapacity()) ? slot.getMaxCapacity() : currSlotCapacity + 1;
        slot.setCurrCapacity(currSlotCapacity);
        slotsrepo.save(slot);
        //TODO : DO I NEED TO DELETE IT?
        //TODO : SEND CONFIRMATION MAIL
        return ResponseEntity.ok("Booking is Denied");
    }

    public ResponseEntity<ArrayList<Booking>> viewAllBooking() {
        return ResponseEntity.ok((ArrayList<Booking>)bookingRepo.findAll());
    }
    public ResponseEntity<ArrayList<Booking>> viewAllBookingBySlotID(Integer slotID) {
        return ResponseEntity.ok((ArrayList<Booking>)bookingRepo.findAllBySlotID(slotID));
    }
}