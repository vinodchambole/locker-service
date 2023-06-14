package com.bank.locker.exception;

public class LockerNotFoundException extends RuntimeException {
    public LockerNotFoundException(String s) {
        super(s);
    }
}
