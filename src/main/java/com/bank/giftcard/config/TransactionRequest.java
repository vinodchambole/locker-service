package com.bank.giftcard.config;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class TransactionRequest {
    private Long accountId;
    private double amount;
    private String transactionPassword;
}
