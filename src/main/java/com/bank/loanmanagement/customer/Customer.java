package com.bank.loanmanagement.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Entity
public class Customer {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String accountNumber;
    private String address;
    private String city;
    private String state;
    private String postalCode;
}

