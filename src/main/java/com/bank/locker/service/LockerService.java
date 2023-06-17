package com.bank.locker.service;

import com.bank.locker.config.AccountFeignClient;
import com.bank.locker.config.TransactionRequest;
import com.bank.locker.controller.LockerReservationRequest;
import com.bank.locker.exception.ResourceNotFoundException;
import com.bank.locker.repository.Account;
import com.bank.locker.repository.Locker;
import com.bank.locker.repository.LockerRepository;
import com.bank.locker.repository.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class LockerService {
    @Autowired
    private LockerRepository lockerRepository;
    @Autowired
    private AccountFeignClient accountFeignClient;

    public List<Locker> getAvailableLockers(Size size) {
        return lockerRepository.findBySizeAndAvailability(size, true);
    }

    public Locker reserveLocker(LockerReservationRequest request) {

        ResponseEntity<List<Account>> accountByEmail = accountFeignClient.getAccountByEmail(request.getCustomer());
        if (Objects.requireNonNull(accountByEmail.getBody()).isEmpty()) {
            throw new UsernameNotFoundException("User account does not exist. Please register.");
        }
        Account account = accountByEmail
                .getBody()
                .stream().filter(a -> Objects.equals(a.getId(), request.getAccountId()))
                .findFirst().orElseThrow((() -> new ResourceNotFoundException("Account with Id: " + request.getAccountId() + " does not exist.")));

        Locker locker = lockerRepository.findByIdAndAvailability(request.getLockerId(), true);

        if (locker != null) {
            accountFeignClient.withdrawBalance(TransactionRequest.builder().accountId(account.getId())
                    .amount(lockerRentalFee(locker.getSize())).build());

            locker.setAvailability(false);
            locker.setCustomer(request.getCustomer());

            lockerRepository.save(locker);
            return locker;
        }
        throw new ResourceNotFoundException("Locker not available.");
    }

    public double lockerRentalFee(Size lockerSize) {
        double rentalFee = 0.0;

        switch (lockerSize) {
            case S:
                rentalFee = 1000.0;
                break;
            case M:
                rentalFee = 1500.0;
                break;
            case L:
                rentalFee = 2000.0;
                break;
            default:
                rentalFee = -1; // Invalid size
                break;
        }
        return rentalFee;
    }

}
