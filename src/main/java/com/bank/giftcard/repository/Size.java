package com.bank.giftcard.repository;

public enum Size {
    S("Small"),
    M("Medium"),
    L("Large");

    private final String label;

    Size(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
