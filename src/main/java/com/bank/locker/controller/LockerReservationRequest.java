package com.bank.locker.controller;

import lombok.Data;

@Data
public class LockerReservationRequest {
    private Long lockerId;
    private String customer;
    private Long accountId;

}
