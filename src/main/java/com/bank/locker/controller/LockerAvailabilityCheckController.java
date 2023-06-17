package com.bank.locker.controller;

import com.bank.locker.repository.Locker;
import com.bank.locker.repository.Size;
import com.bank.locker.service.LockerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lockers")
public class LockerAvailabilityCheckController {

    @Autowired
    private LockerService lockerService;


    @GetMapping("/availability")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<Locker>> checkLockerAvailability(@RequestParam("size") String size) {
        List<Locker> availableLockers = lockerService.getAvailableLockers(Size.valueOf(size));
        if (availableLockers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(availableLockers);
    }

    @PostMapping("/reserve")
    public ResponseEntity<Locker> reserveLocker(@RequestBody LockerReservationRequest request) {

        Locker reservedLocker = lockerService.reserveLocker(request);
        if (reservedLocker == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(reservedLocker);
    }
}
