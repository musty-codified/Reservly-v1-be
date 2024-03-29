package com.mustycodified.Reservlyv1be.enums;

public enum TransactionType {
    TRANSACTION_TYPE_TRANSFER ("transaction:transfer"),
    TRANSACTION_TYPE_DEPOSIT("transaction:deposit");

    private final String transaction;
    TransactionType(String transaction) {
        this.transaction = transaction;
    }
    public String getTransaction() {
        return transaction;
    }

}
