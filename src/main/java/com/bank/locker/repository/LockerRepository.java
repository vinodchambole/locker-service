package com.bank.locker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LockerRepository extends JpaRepository<Locker, Long> {
    List<Locker> findBySizeAndAvailability(Size size, boolean availability);
    Locker findByIdAndAvailability(Long id, boolean availability);
}

