package com.bank.locker.service;

import com.bank.locker.controller.LockerReservationRequest;
import com.bank.locker.exception.LockerNotFoundException;
import com.bank.locker.repository.Locker;
import com.bank.locker.repository.LockerRepository;
import com.bank.locker.repository.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LockerAvailabilityCheckService {
    private final LockerRepository lockerRepository;

    @Autowired
    public LockerAvailabilityCheckService(LockerRepository lockerRepository) {
        this.lockerRepository = lockerRepository;
    }

    public List<Locker> getAvailableLockers(Size size) {
        return lockerRepository.findBySizeAndAvailability(size, true);
    }

    public Locker reserveLocker(LockerReservationRequest request) {
        Locker locker = lockerRepository.findByIdAndAvailability(request.getLockerId(), true);
        if (locker != null) {
            locker.setAvailability(false);
            locker.setCustomer(request.getCustomer());
            lockerRepository.save(locker);
            return locker;
        }
        throw new LockerNotFoundException("Locker not found");
    }
}
