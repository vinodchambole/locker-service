package com.bank.giftcard.controller;

import lombok.Data;

@Data
public class LockerReservationRequest {
    private Long lockerId;
    private String customer;
    private Long accountId;

}
